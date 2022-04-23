package health.real_pt.gym.service;

import health.real_pt.exception.exception_handler.ExceptionType;
import health.real_pt.exception.exceptions.CommonApiExceptions;
import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymReqDto;
import health.real_pt.gym.dto.GymResDto;
import health.real_pt.gym.dto.PtResDto;
import health.real_pt.gym.repository.GymRepository;
import health.real_pt.image.domain.GymImage;
import health.real_pt.image.dto.ImageResDto;
import health.real_pt.image.service.GymImageServiceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
//JPA는 트랜잭션 안에서 실행됨, readonly = true로 설정하면 DB에 커밋이 되지 않아(영속성 컨텍스트 플러시가 안됨) 등록, 수정 ,삭제 등이 발생하지 않음
public class GymServiceImpl implements GymService {

    private final GymRepository gymRepository;
    private final GymImageServiceImpl gymImageService;

    public GymServiceImpl(GymRepository gymRepository, GymImageServiceImpl gymImageService) {
        this.gymRepository = gymRepository;
        this.gymImageService = gymImageService;
    }

    @Override
    @Transactional
    public Long saveGym(GymReqDto gymReqDto, List<MultipartFile> files) {
        Gym gym = Gym.toEntity(gymReqDto);

        checkDuplicateGymName(gymReqDto.getName());

        gymImageService.uploadFiles(files, gym);

        return gymRepository.save(gym);
    }

    //헬스장 이름 중복 체크
    private void checkDuplicateGymName(String name) {
        List<Gym> gymList = gymRepository.findByName(name);
        if (!gymList.isEmpty())
            throw new DuplicateKeyException("동일한 헬스장 이름이 존재합니다!");
    }

    @Override
    @Transactional
    public GymResDto updateGym(Long id, GymReqDto gymReqDto, List<MultipartFile> files) {
        Gym gym = findEntity(id);

        //Gym 수정
        gym.updateEntity(gymReqDto);

        //헬스장 사진 수정(삭제 후 다시 생성)
        for (GymImage image : gym.getImages())
            gymImageService.deleteFiles(image.getFilepath());

        //헬스장 이미지 엔티티 참조 제거
        gym.deleteGymImages();

        //이미지 새로 등록
        gymImageService.uploadFiles(files, gym);

        return new GymResDto().entityToDto(gym, getGymImages(gym.getImages()));
    }

    //멤버 이미지 -> ImageResDto로 변환
    private List<ImageResDto> getGymImages(List<GymImage> images) {
        return images.stream()
                .map(image -> new ImageResDto(image.getOriginalFileName(),image.getDownloadUri()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GymResDto> findGyms() {
        List<Gym> gyms = gymRepository.findAll();

        return gyms.stream()
                .map(gym -> new GymResDto().entityToDto(gym,getGymImages(gym.getImages())))
                .collect(Collectors.toList());
    }

    @Override
    public GymResDto findOne(Long id) {
        Gym gym = findEntity(id);

        return new GymResDto().entityToDto(gym, getGymImages(gym.getImages()));
    }

    @Override
    public List<PtResDto> findPtBelongGym(Long id) {
        Gym gym = findEntity(id);

        return gym.getPt().stream()
                .map(m -> PtResDto.builder()
                        .gymName(gym.getName())
                        .location(gym.getLocation())
                        .name(m.getName())
                        .representImageUrl(m.getImages().size() > 0 ? m.getImages().get(0).getDownloadUri() : null)
                        .build())
                .collect(Collectors.toList());

    }

    @Transactional
    @Override
    public void deleteGym(Long id) {
        Gym gym = findEntity(id);
        gym.deleteGym();

        gymRepository.delete(gym);  //GYM - PT - PRICE - IMAGE
    }

    @Override
    public Gym findEntity(Long id) {
        return gymRepository.findById(id).orElseThrow(() ->
                new CommonApiExceptions(ExceptionType.ENTITY_NOT_FOUND_EXCEPTION, "id = " + id + "인 Gym 객체를 찾을 수 없습니다.")
        );
    }


}
