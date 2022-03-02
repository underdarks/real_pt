package health.real_pt.gym.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymDto;
import health.real_pt.gym.repository.GymRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional  //JPA는 트랜잭션 안에서 실행됨
public class GymServiceImpl implements GymService{

    private final GymRepository gymRepository;

    public GymServiceImpl(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Override
    public void saveGym(GymDto gymDto) {
        Gym gym = Gym.toEntity(gymDto);
        gymRepository.save(gym);
    }

    @Override
    public void updateGym(GymDto gymDto) {
        Gym updateGym = gymRepository.findById(gymDto.getId()).get();


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
    public void deleteGym(Long id) {
        Gym gym = gymRepository.findById(id).get();
        gymRepository.delete(gym);
    }
}
