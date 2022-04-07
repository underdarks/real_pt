package health.real_pt.price.service.ptPrice;

import health.real_pt.member.domain.Member;
import health.real_pt.member.repository.MemberRepository;
import health.real_pt.price.domain.PtPrice;
import health.real_pt.price.dto.ptPrice.PtPriceReqDto;
import health.real_pt.price.dto.ptPrice.PtPriceResDto;
import health.real_pt.price.repository.ptPrice.PtPriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PtPriceServiceImpl implements PtPriceService {

    private final MemberRepository memberRepository;
    private final PtPriceRepository ptPriceRepository;

    public PtPriceServiceImpl(MemberRepository memberRepository, PtPriceRepository ptPriceRepository) {
        this.memberRepository = memberRepository;
        this.ptPriceRepository = ptPriceRepository;
    }

    @Transactional
    @Override
    public Long savePrice(PtPriceReqDto ptPriceReqDto, Long memberId) {
        //멤버 찾기
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Member 객체를 찾을 수 없습니다!"));
        ptPriceReqDto.setPt(member);

        //dto -> entity
        PtPrice ptPrice = PtPrice.toEntity(ptPriceReqDto);

        return ptPriceRepository.save(ptPrice);
    }

    @Transactional
    @Override
    public PtPriceResDto updatePrice(PtPriceReqDto updDto) {
        PtPrice ptPrice = ptPriceRepository.findById(updDto.getId()).orElseThrow(() -> new NoSuchElementException("PtPrice 객체를 찾을 수 없습니다."));
        ptPrice.updateEntity(updDto);   //더티 체킹(엔티티 변경)

        return new PtPriceResDto().entityToDto(ptPrice);
    }

    @Transactional
    @Override
    public void deletePrice(Long id) {
        PtPrice ptPrice = ptPriceRepository.findById(id).orElseThrow(() -> new NoSuchElementException("PtPrice 객체를 찾을 수 없습니다."));
        ptPriceRepository.delete(ptPrice);
    }

    @Override
    public PtPriceResDto findOnePrice(Long ptPriceId) {
        PtPrice ptPrice = ptPriceRepository.findById(ptPriceId).orElseThrow(() -> new NoSuchElementException("PtPrice 객체를 찾을 수 없습니다."));
        return new PtPriceResDto().entityToDto(ptPrice);
    }

    @Override
    public List<PtPriceResDto> findAllPrice(Long gymId, Long ptId) {
        List<PtPrice> ptPriceList = ptPriceRepository.findAll(gymId, ptId);

        return ptPriceList.stream()
                .map(ptPrice -> new PtPriceResDto().entityToDto(ptPrice))
                .collect(Collectors.toList());
    }
}
