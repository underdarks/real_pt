package health.real_pt.gym.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymDto;
import health.real_pt.gym.repository.GymRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)  //JPA는 트랜잭션 안에서 실행됨, readonly = true로 설정하면 DB에 커밋이 되지 않아(영속성 컨텍스트 플러시가 안됨) 등록, 수정 ,삭제 등이 발생하지 않음
public class GymServiceImpl implements GymService{

    private final GymRepository gymRepository;

    public GymServiceImpl(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Override
    @Transactional
    public Long saveGym(GymDto gymDto) {
        Gym gym = Gym.toEntity(gymDto);
        return gymRepository.save(gym);
    }

    @Override
    @Transactional
    public void updateGym(Long gymId, GymDto gymDto) {
        Gym gym = gymRepository.findById(gymId).orElseThrow(() -> new NoSuchElementException("Gym 객체를 찾을 수 없습니다!"));
        gym.updateEntity(gymDto);
    }

    @Override
    public List<Gym> findGyms() {
        return gymRepository.findAll();
    }

    @Override
    public Gym findOne(Long id) {
        return gymRepository.findById(id).orElseThrow(() -> new NoSuchElementException("id = " + id + "인 Gym 객체를 찾을 수 없습니다."));
    }

    @Transactional
    @Override
    public void deleteGym(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Gym 객체를 찾을 수 없습니다!"));
        gymRepository.delete(gym);
    }

}
