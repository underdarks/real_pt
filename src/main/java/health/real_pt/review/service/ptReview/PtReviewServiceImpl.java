package health.real_pt.review.service.ptReview;

import health.real_pt.price.domain.PtPrice;
import health.real_pt.review.domain.PtReview;
import health.real_pt.review.dto.ptReview.PtReviewReqDto;
import health.real_pt.review.dto.ptReview.PtReviewResDto;
import health.real_pt.review.repository.ptReview.PtReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional(readOnly = true)
@Service
public class PtReviewServiceImpl implements PtReviewService{

    private final PtReviewRepository ptReviewRepository;

    public PtReviewServiceImpl(PtReviewRepository ptReviewRepository) {
        this.ptReviewRepository = ptReviewRepository;
    }

    @Override
    public Long saveReview(PtReviewReqDto reqDto) {
        PtReview ptReview = PtReview.toEntity(reqDto);
        return ptReviewRepository.save(ptReview);
    }

    @Override
    public PtReviewResDto updateReview(PtReviewReqDto updDto) {
        PtReview ptReview = ptReviewRepository.findById(updDto.getId()).orElseThrow(() -> new NoSuchElementException("PtReview 객체를 찾을 수 없습니다!"));

        //엔티티 수정
        ptReview.updateEntity(updDto);

        return new PtReviewResDto().entityToDto(ptReview);
    }

    @Override
    public List<PtReviewResDto> findAllReviews(Long gymId, Long ptId) {
        ptReviewRepository.findAll(gymId,ptId,"")   //최근 작성일(디폴트)
    }

    @Override
    public List<PtReviewResDto> findAllSortedReviews(Long gymId, Long ptId) {
        return null;
    }

    @Override
    public void deleteReview(Long id) {

    }

    @Override
    public Long addGood(Long id) {
        return null;
    }

    @Override
    public Long subGood(Long id) {
        return null;
    }

    @Override
    public Long addBad(Long id) {
        return null;
    }

    @Override
    public Long subBad(Long id) {
        return null;
    }
}
