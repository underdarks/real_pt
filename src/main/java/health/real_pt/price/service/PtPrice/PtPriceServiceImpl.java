package health.real_pt.price.service.PtPrice;

import health.real_pt.member.domain.Member;
import health.real_pt.member.repository.MemberRepository;
import health.real_pt.price.domain.PtPrice;
import health.real_pt.price.dto.PtPrice.PtPriceDto;
import health.real_pt.price.repository.PtPriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Long savePtPrice(PtPriceDto ptPriceDto, Long memberId) {
        //1.멤버 찾기
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        memberOptional.ifPresent(member ->
                ptPriceDto.setTrainer(member));

        //2.dto -> entity
        PtPrice ptPrice = PtPrice.toEntity(ptPriceDto);
        return ptPriceRepository.save(ptPrice);
    }

    @Override
    public void updatePtPrice(PtPriceDto ptPriceDto) {
        //1. 영속성 컨텍스트에서 꺼내온다
        Optional<PtPrice> ptPriceOptional = ptPriceRepository.findById(ptPriceDto.getId());
        ptPriceOptional.ifPresent(ptPrice ->
                        ptPrice.updateEntity(ptPriceDto)
                );
    }

    @Override
    public void deletePtPrice(PtPriceDto ptPriceDto) {
        Optional<PtPrice> ptPriceOptional = ptPriceRepository.findById(ptPriceDto.getId());
        ptPriceOptional.ifPresent(ptPrice ->
                        ptPriceRepository.delete(ptPrice)
                );
    }

    @Override
    public Optional<PtPrice> findOnePrice(Long ptPriceId) {
        Optional<PtPrice> ptPriceOptional = ptPriceRepository.findById(ptPriceId);
        return ptPriceOptional;
    }

    @Override
    public List<PtPrice> findAllPrice() {
        return ptPriceRepository.findAll();
    }
}
