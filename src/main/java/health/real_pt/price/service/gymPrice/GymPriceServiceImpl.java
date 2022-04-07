package health.real_pt.price.service.gymPrice;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.repository.GymRepository;
import health.real_pt.price.dto.gymPrice.GymPriceResDto;
import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.dto.gymPrice.GymPriceReqDto;
import health.real_pt.price.repository.gymPrice.GymPriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
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
    public Long savePrice(GymPriceReqDto gymPriceReqDto, Long gymId) {
        //헬스장 엔티티 찾기
        Gym gym  = gymRepository.findById(gymId).orElseThrow(() -> new NoSuchElementException("gymId= " + gymId + "인 Gym 객체를 찾을 수 없습니다"));;

        gymPriceReqDto.setGym(gym);
        GymPrice gymPrice = GymPrice.toEntity(gymPriceReqDto);//Dto -> Entiiy

        return gymPriceRepository.save(gymPrice);
    }

    @Transactional
    @Override
    public GymPriceResDto updatePrice(GymPriceReqDto gymPriceReqDto) {
        GymPrice gymPrice = gymPriceRepository.findById(gymPriceReqDto.getId()).orElseThrow(() ->
                new NoSuchElementException("gymId= " + gymPriceReqDto.getGym().getId() + "인 GymPrice 객체를 찾을 수 없습니다"));;

        gymPrice.updateEntity(gymPriceReqDto);
        return new GymPriceResDto().entityToDto(gymPrice);
    }

    @Transactional
    @Override
    public void deletePrice(Long id) {
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
    public List<GymPriceResDto> findAllPrice(Long gymId) {
        List<GymPrice> gymPriceList = gymPriceRepository.findByGymId(gymId);

        //Entity List -> Dto List
        return   gymPriceList.stream()
                .map(gymPrice -> new GymPriceResDto().entityToDto(gymPrice))
                .collect(Collectors.toList());
    }

}
