package health.real_pt.price.service.GymPrice;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.repository.GymRepository;
import health.real_pt.price.api.GymPrice.GymPriceListDto;
import health.real_pt.price.api.GymPrice.GymPriceResDto;
import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.dto.GymPrice.GymPriceDto;
import health.real_pt.price.repository.GymPriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Long saveGymPrice(GymPriceDto gymPriceDto,Long gymId) {
        //헬스장 엔티티 찾기
        Gym gym  = gymRepository.findById(gymId).orElseThrow(() -> new NoSuchElementException("gymId= " + gymId + "인 Gym 객체를 찾을 수 없습니다"));;

        gymPriceDto.setGym(gym);
        GymPrice gymPrice = GymPrice.toEntity(gymPriceDto);//Dto -> Entiiy

        return gymPriceRepository.save(gymPrice);
    }

    @Transactional
    @Override
    public GymPriceResDto updateGymPrice(GymPriceDto gymPriceDto) {
        GymPrice gymPrice = gymPriceRepository.findById(gymPriceDto.getId()).orElseThrow(() ->
                new NoSuchElementException("gymId= " + gymPriceDto.getGym().getId() + "인 GymPrice 객체를 찾을 수 없습니다"));;

        gymPrice.updateEntity(gymPriceDto);
        return new GymPriceResDto().entityToDto(gymPrice);
    }

    @Transactional
    @Override
    public void deleteGymPrice(Long id) {
        GymPrice gymPrice = gymPriceRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("gymId= " + id + "인 GymPrice 객체를 찾을 수 없습니다"));;

        gymPriceRepository.delete(gymPrice);
    }

    @Override
    public GymPriceResDto findOnePrice(Long gymPriceId) {
        GymPrice gymPrice = gymPriceRepository.findById(gymPriceId).orElseThrow(() -> new NoSuchElementException("GymPrice 객체를 찾을 수 없습니다!"));

        //Entity --> DTO
        return new GymPriceResDto().entityToDto(gymPrice);
    }

    @Override
    public GymPriceListDto findAllPrice(Long gymId) {
        List<GymPrice> gymPriceList = gymPriceRepository.findByGymId(gymId);

        //Entity List -> Dto List
        List<GymPriceResDto> priceDtoList = gymPriceList.stream()
                .map(gymPrice -> new GymPriceResDto().entityToDto(gymPrice))
                .collect(Collectors.toList());

        return new GymPriceListDto(priceDtoList.size(),priceDtoList);
    }

}
