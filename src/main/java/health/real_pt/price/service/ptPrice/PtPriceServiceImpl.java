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

@Service
@Transactional(readOnly = true)
public class PtPriceServiceImpl implements  PtPriceService{

    private final MemberRepository memberRepository;
    private final PtPriceRepository ptPriceRepository;

    public PtPriceServiceImpl(MemberRepository memberRepository, PtPriceRepository ptPriceRepository) {
        this.memberRepository = memberRepository;
        this.ptPriceRepository = ptPriceRepository;
    }

    @Override
    public Long savePtPrice(PtPriceReqDto ptPriceReqDto, Long memberId) {
        //멤버 찾기
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("PtPrice 객체를 찾을 수 없습니다!"));

        //dto -> entity
        PtPrice ptPrice = PtPrice.toEntity(ptPriceReqDto);
        ptPrice.changeTrainer(member);

        return ptPriceRepository.save(ptPrice);
    }

    @Override
    public PtPriceResDto updatePtPrice(PtPriceReqDto updDto) {
        PtPrice ptPrice = ptPriceRepository.findById(updDto.getId()).orElseThrow(() -> new NoSuchElementException("PtPrice 객체를 찾을 수 없습니다."));
        ptPrice.updateEntity(updDto);   //더티 체킹(엔티티 변경)

        return new PtPriceResDto().entityToDto(ptPrice);
    }

    @Override
    public void deletePtPrice(Long id) {
        PtPrice ptPrice = ptPriceRepository.findById(id).orElseThrow(() -> new NoSuchElementException("PtPrice 객체를 찾을 수 없습니다."));
        ptPriceRepository.delete(ptPrice);
    }

    @Override
    public PtPriceResDto findOnePrice(Long ptPriceId) {
        PtPrice ptPrice = ptPriceRepository.findById(ptPriceId).orElseThrow(() -> new NoSuchElementException("PtPrice 객체를 찾을 수 없습니다."));
        return new PtPriceResDto().entityToDto(ptPrice);
    }

    @Override
    public List<PtPrice> findAllPrice() {
        return ptPriceRepository.findAll();
    }
}
