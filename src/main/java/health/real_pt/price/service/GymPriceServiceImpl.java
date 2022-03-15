package health.real_pt.price.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.repository.GymRepository;
import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.dto.GymPriceDto;
import health.real_pt.price.repository.GymPriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GymPriceServiceImpl implements GymPriceService {

    private final GymPriceRepository gymPriceRepository;
    private final GymRepository gymRepository;

    public GymPriceServiceImpl(GymPriceRepository gymPriceRepository, GymRepository gymRepository) {
        this.gymPriceRepository = gymPriceRepository;
        this.gymRepository = gymRepository;
    }


    @Transactional
    @Override
    public Long saveGymPrice(GymPriceDto gymPriceDto, Long memberId, Long gymId) {
        //헬스장 엔티티 찾기
        Optional<Gym> gymOptional = gymRepository.findById(gymId);
        gymOptional.ifPresent(gym ->
                gymPriceDto.setGym(gym)
                );

        //Dto -> Entiiy
        GymPrice gymPrice = GymPrice.toEntity(gymPriceDto);

        return gymPriceRepository.save(gymPrice);
    }

    @Transactional
    @Override
    public void updateGymPrice(GymPriceDto gymPriceDto) {
        Optional<GymPrice> gpOptional = gymPriceRepository.findById(gymPriceDto.getId());
        gpOptional.ifPresent(gymPrice ->
                gymPrice.updateEntity(gymPriceDto)
                );
    }

    @Transactional
    @Override
    public void deleteGymPrice(GymPriceDto gymPriceDto) {
        Optional<GymPrice> gpOptional = gymPriceRepository.findById(gymPriceDto.getId());
        gpOptional.ifPresent(gymPrice->
                gymPriceRepository.delete(gymPrice)
                );
    }

    @Override
    public Optional<GymPrice> findOnePrice(Long gymPriceId) {
        Optional<GymPrice> gpOptional = gymPriceRepository.findById(gymPriceId);
        return gpOptional;
    }

    @Override
    public List<GymPrice> findAllPrice(Long gymId) {
        return gymPriceRepository.findByGymId(gymId);
    }
}
