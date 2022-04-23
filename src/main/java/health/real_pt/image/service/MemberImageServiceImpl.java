package health.real_pt.image.service;

import health.real_pt.exception.exception_handler.ExceptionType;
import health.real_pt.exception.exceptions.CommonApiExceptions;
import health.real_pt.image.domain.MemberImage;
import health.real_pt.image.repository.MemberImageRepository;
import health.real_pt.member.domain.Member;
import health.real_pt.security.encryption.MD5;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberImageServiceImpl extends FileManager implements ImageService<Member> {

    private final MemberImageRepository memberImageRepository;
    public final static Path fileLocation = Paths.get(MemberImage.serverFilePath).toAbsolutePath().normalize();

    @Transactional
    @Override
    public void uploadFiles(List<MultipartFile> files, Member member) {
        Random random = new Random();

        for (MultipartFile file : files) {
            try {
                if(!file.getOriginalFilename().isEmpty()) {
                    String filename = StringUtils.cleanPath(file.getOriginalFilename());

                    if (filename.contains(".."))
                        throw new CommonApiExceptions(ExceptionType.FILE_UPLOAD_EXCEPTION, "파일명에 부적합 문자가 포함되어 있습니다." + filename);

                    String[] ext = filename.split("[.]");     //확장자 구분

                    //파일 이름 중복 방지를 위한(파일 이름 암호화(MD5) + random 함수 적용)
                    String encryptedFileName = MD5.getMD5HashCode(filename) + random.nextInt(100000000) + "." + ext[1];
                    String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("image/member/" + encryptedFileName).toUriString();

                    //멤버 이미지 객체 생성
                    MemberImage uploadFile = MemberImage.builder()
                            .member(member)
                            .storedFileName(encryptedFileName)
                            .originalFileName(filename)
                            .downloadUri(downloadUri)
                            .size(file.getSize())
                            .filepath(MemberImage.serverFilePath + encryptedFileName)
                            .build();


                    //파일 전송
                    file.transferTo(fileLocation.resolve(encryptedFileName));

                    memberImageRepository.save(uploadFile);
                }
            }
            catch (IOException e) {
                throw new CommonApiExceptions(ExceptionType.FILE_UPLOAD_EXCEPTION,"다시 시도하십시오");
            }

        }
    }

}
