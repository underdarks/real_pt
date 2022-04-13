package health.real_pt.price.service.gymPrice;

import health.real_pt.common.exception_handler.ExceptionType;
import health.real_pt.common.exceptions.CommonApiExceptions;
import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.service.GymService;
import health.real_pt.price.dto.gymPrice.GymPriceReqResDto;
import health.real_pt.price.dto.gymPrice.GymPriceResResDto;
import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.repository.gymPrice.GymPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GymPriceServiceImpl implements GymPriceService {

    private final GymPriceRepository gymPriceRepository;
    private final GymService gymService;


    @Transactional
    @Override
    public Long savePrice(GymPriceReqResDto gymPriceReqDto, Long gymId) {
        //헬스장 엔티티 찾기
        Gym gym = gymService.findOne(gymId);

        gymPriceReqDto.setGym(gym);
        GymPrice gymPrice = GymPrice.toEntity(gymPriceReqDto);//Dto -> Entiiy

        return gymPriceRepository.save(gymPrice);
    }

    @Transactional
    @Override
    public GymPriceResResDto updatePrice(GymPriceReqResDto reqDto) {
        GymPrice gymPrice = findEntity(reqDto.getId());

        gymPrice.updateEntity(reqDto);
        return new GymPriceResResDto().entityToDto(gymPrice);
    }

    @Transactional
    @Override
    public void deletePrice(Long id) {
        GymPrice gymPrice = findEntity(id);
        gymPriceRepository.delete(gymPrice);
    }

    @Override
    public GymPriceResResDto findOnePrice(Long id) {
        GymPrice gymPrice = findEntity(id);

        //Entity --> DTO
        return new GymPriceResResDto().entityToDto(gymPrice);
    }

    @Override
    public List<GymPriceResResDto> findAllPrice(Long gymId) {
        List<GymPrice> gymPriceList = gymPriceRepository.findByGymId(gymId);

        //Entity List -> Dto List
        return gymPriceList.stream()
                .map(gymPrice -> new GymPriceResResDto().entityToDto(gymPrice))
                .collect(Collectors.toList());
    }

    @Override
    public GymPrice findEntity(Long id) {
        return gymPriceRepository.findById(id).orElseThrow(() ->
                new CommonApiExceptions(ExceptionType.ENTITY_NOT_FOUND_EXCEPTION, "id = " + id + "인 GymPrice 객체를 찾을 수 없습니다.")
        );
    }
}
