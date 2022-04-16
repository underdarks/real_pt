package health.real_pt.image.service;

import health.real_pt.common.exception_handler.ExceptionType;
import health.real_pt.common.exceptions.CommonApiExceptions;
import health.real_pt.image.domain.PtReviewFile;
import health.real_pt.image.repository.PtReviewFileRepository;
import health.real_pt.review.domain.PtReview;
import health.real_pt.security.encryption.MD5;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PtReviewFileServiceImpl implements FileManagerService {

    private final PtReviewFileRepository fileRepository;

    @Transactional
    @Override
    public void uploadFiles(List<MultipartFile> files, PtReview review) {
        Random random=new Random();

        for (MultipartFile file : files) {
            try {

                Path fileLocation = Paths.get(PtReviewFile.serverFilePath).toAbsolutePath().normalize();
                String filename = StringUtils.cleanPath(file.getOriginalFilename());

                //파일 이름 암호화(MD5)
                String encryptedFileName = MD5.getMD5HashCode(filename)+random.nextInt(10000);

                PtReviewFile uploadFile = PtReviewFile.builder()
                        .ptReview(review)
                        .storedFileName(encryptedFileName)
                        .originalFileName(filename)
                        .size(file.getSize())
                        .filepath(PtReviewFile.serverFilePath + encryptedFileName)
                        .build();

                //파일 전송
                file.transferTo(fileLocation.resolve(encryptedFileName));

                //파일 복사
//                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                fileRepository.save(uploadFile);

            } catch (IOException e) {
                throw new CommonApiExceptions(ExceptionType.FILE_UPLOAD_EXCEPTION,e.getMessage());
            }

        }
    }
}
