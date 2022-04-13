package health.real_pt.price.service.ptPrice;

import health.real_pt.common.exception_handler.ExceptionType;
import health.real_pt.common.exceptions.CommonApiExceptions;
import health.real_pt.member.service.MemberService;
import health.real_pt.price.domain.PtPrice;
import health.real_pt.price.dto.ptPrice.PtPriceReqDto;
import health.real_pt.price.dto.ptPrice.PtPriceResResDto;
import health.real_pt.price.repository.ptPrice.PtPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PtPriceServiceImpl implements PtPriceService {

    private final PtPriceRepository ptPriceRepository;
    private final MemberService memberService;


    @Transactional
    @Override
    public Long savePrice(PtPriceReqDto reqDto, Long memberId) {
        //멤버 찾기
//        Member member = memberService.findEntity(memberId);
//
//        reqDto.setPt(member);
//
//        //dto -> entity
//        PtPrice ptPrice = PtPrice.toEntity(reqDto);
//
//        return ptPriceRepository.save(ptPrice);
    }

    @Transactional
    @Override
    public PtPriceResResDto updatePrice(PtPriceReqDto updDto) {
        PtPrice ptPrice = findEntity(updDto.getId());
        ptPrice.updateEntity(updDto);   //더티 체킹(엔티티 변경)

        return new PtPriceResResDto().entityToDto(ptPrice);
    }

    @Transactional
    @Override
    public void deletePrice(Long id) {
        PtPrice ptPrice = findEntity(id);
        ptPriceRepository.delete(ptPrice);
    }

    @Override
    public PtPriceResResDto findOnePrice(Long id) {
        PtPrice ptPrice = findEntity(id);
        return new PtPriceResResDto().entityToDto(ptPrice);
    }

    @Override
    public List<PtPriceResResDto> findAllPrice(Long gymId, Long ptId) {
        List<PtPrice> ptPriceList = ptPriceRepository.findAll(gymId, ptId);

        return ptPriceList.stream()
                .map(ptPrice -> new PtPriceResResDto().entityToDto(ptPrice))
                .collect(Collectors.toList());
    }

    @Override
    public PtPrice findEntity(Long id) {
        return ptPriceRepository.findById(id).orElseThrow(() ->
                new CommonApiExceptions(ExceptionType.ENTITY_NOT_FOUND_EXCEPTION,"id = " + id + "인 GymPrice 객체를 찾을 수 없습니다.")
        );
    }
}
