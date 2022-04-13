package health.real_pt.review.service.ptReview;

import health.real_pt.common.exception_handler.ExceptionType;
import health.real_pt.common.exceptions.CommonApiExceptions;
import health.real_pt.member.domain.Member;
import health.real_pt.member.repository.MemberRepository;
import health.real_pt.member.service.MemberService;
import health.real_pt.review.domain.PtReview;
import health.real_pt.review.dto.ptReview.PtReviewReqDto;
import health.real_pt.review.dto.ptReview.PtReviewResDto;
import health.real_pt.review.repository.ptReview.PtReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class PtReviewServiceImpl implements PtReviewService{

    private final PtReviewRepository ptReviewRepository;
    private final MemberService memberService;

    public PtReviewServiceImpl(PtReviewRepository ptReviewRepository, MemberService memberService) {
        this.ptReviewRepository = ptReviewRepository;
        this.memberService = memberService;
    }

    @Transactional
    @Override
    public Long saveReview(PtReviewReqDto reqDto, Long memberId) {
        Member member = memberService.findEntity(memberId);

        reqDto.setPt(member);
        PtReview ptReview = PtReview.toEntity(reqDto);

        return ptReviewRepository.save(ptReview);
    }

    @Transactional
    @Override
    public PtReviewResDto updateReview(PtReviewReqDto updDto) {
        PtReview ptReview = findEntity(updDto.getId());

        //엔티티 수정
        ptReview.updateEntity(updDto);

        return new PtReviewResDto().entityToDto(ptReview);
    }

    @Override
    public List<PtReviewResDto> findAllReviews(Long gymId, Long ptId) {
        //최근 작성일 기준 정렬(디폴트 값)
        List<PtReview> ptReviewList = ptReviewRepository.findAll(gymId, ptId, "reg_date desc");

        return ptReviewList.stream()
                .map(pr -> new PtReviewResDto().entityToDto(pr))
                .collect(Collectors.toList());
    }

    @Override
    public List<PtReviewResDto> findAllSortedReviews(Long gymId, Long ptId, String sortType) {
        return null;
    }

    @Transactional
    @Override
    public void deleteReview(Long id) {
        PtReview ptReview = findEntity(id);
        ptReviewRepository.delete(ptReview);
    }

    @Override
    public PtReview findEntity(Long id) {
        return ptReviewRepository.findById(id).orElseThrow(() ->
                new CommonApiExceptions(ExceptionType.ENTITY_NOT_FOUND_EXCEPTION,"id = " + id + "인 PtReview 객체를 찾을 수 없습니다.")
        );
    }


    @Transactional
    @Override
    public Long addGood(Long id) {
        PtReview ptReview = findEntity(id);
        ptReview.addGood();

        return ptReview.getGood();
    }

    @Transactional
    @Override
    public Long subGood(Long id) {
        PtReview ptReview = findEntity(id);
        ptReview.subGood();

        return ptReview.getGood();
    }

    @Transactional
    @Override
    public Long addBad(Long id) {
        PtReview ptReview = findEntity(id);
        ptReview.addBad();

        return ptReview.getBad();
    }

    @Transactional
    @Override
    public Long subBad(Long id) {
        PtReview ptReview = findEntity(id);
        ptReview.subBad();

        return ptReview.getBad();
    }

}
