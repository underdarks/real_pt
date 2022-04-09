package health.real_pt.review.service.ptReview;

import health.real_pt.member.domain.Member;
import health.real_pt.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    public PtReviewServiceImpl(PtReviewRepository ptReviewRepository, MemberRepository memberRepository) {
        this.ptReviewRepository = ptReviewRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public PtReview findPtRview(Long id){
        PtReview ptReview = ptReviewRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ptReview 객체를 찾을 수 없습니다."));
        return ptReview;
    }

    @Transactional
    @Override
    public Long saveReview(PtReviewReqDto reqDto, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        reqDto.setPt(member);
        PtReview ptReview = PtReview.toEntity(reqDto);
        return ptReviewRepository.save(ptReview);
    }

    @Transactional
    @Override
    public PtReviewResDto updateReview(PtReviewReqDto updDto) {
        PtReview ptReview = ptReviewRepository.findById(updDto.getId()).orElseThrow(() -> new NoSuchElementException("PtReview 객체를 찾을 수 없습니다!"));

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
        PtReview ptReview = ptReviewRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ptReview 객체를 찾을 수 없습니다."));
        ptReviewRepository.delete(ptReview);
    }

    @Transactional
    @Override
    public Long addGood(Long id) {
        PtReview ptReview = findPtRview(id);
        ptReview.addGood();

        return ptReview.getGood();
    }

    @Transactional
    @Override
    public Long subGood(Long id) {
        return null;
    }

    @Transactional
    @Override
    public Long addBad(Long id) {
        return null;
    }

    @Transactional
    @Override
    public Long subBad(Long id) {
        return null;
    }
}
