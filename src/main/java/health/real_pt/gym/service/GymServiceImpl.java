package health.real_pt.gym.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymDto;
import health.real_pt.gym.repository.GymRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public void saveGym(GymDto gymDto) {
        Gym gym = Gym.toEntity(gymDto);
        gymRepository.save(gym);
    }

    @Override
    @Transactional
    public void updateGym(GymDto gymDto) {
        Gym gym = gymRepository.findById(gymDto.getId()).get();
        gym.updateEntity(gymDto);
    }

    @Override
    public List<Gym> findGyms() {
        return gymRepository.findAll();
    }

    @Override
    public Optional<Gym> findOne(Long id) {
        return gymRepository.findById(id);
    }

    @Override
    public void deleteGym(GymDto gymDto) {
        Gym gym = gymRepository.findById(gymDto.getId()).get();
        gymRepository.delete(gym);
    }
}
