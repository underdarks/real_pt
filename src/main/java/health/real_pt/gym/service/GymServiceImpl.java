package health.real_pt.gym.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymReqDto;
import health.real_pt.gym.repository.GymRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)  //JPA는 트랜잭션 안에서 실행됨, readonly = true로 설정하면 DB에 커밋이 되지 않아(영속성 컨텍스트 플러시가 안됨) 등록, 수정 ,삭제 등이 발생하지 않음
public class GymServiceImpl implements GymService{

    private final GymRepository gymRepository;

    public GymServiceImpl(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Override
    @Transactional
    public Long saveGym(GymReqDto gymReqDto) {
        Gym gym = Gym.toEntity(gymReqDto);
        return gymRepository.save(gym);
    }

    @Override
    @Transactional
    public void updateGym(Long id, GymReqDto gymReqDto) {
        Gym gym = findEntity(id);
        gym.updateEntity(gymReqDto);
    }

    @Override
    public List<Gym> findGyms() {
        return gymRepository.findAll();
    }

    @Override
    public Gym findOne(Long id) {
        return findEntity(id);
    }

    @Transactional
    @Override
    public void deleteGym(Long id) {
        Gym gym = findEntity(id);
        gymRepository.delete(gym);
    }

    @Transactional
    @Override
    public Gym findEntity(Long id) {
        return gymRepository.findById(id).orElseThrow(() -> new NoSuchElementException("GYM"));
    }
}
