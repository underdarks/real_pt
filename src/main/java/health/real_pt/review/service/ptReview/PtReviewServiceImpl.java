package health.real_pt.review.service.ptReview;

import health.real_pt.review.dto.ptReview.PtReviewReqDto;
import health.real_pt.review.dto.ptReview.PtReviewResDto;
import health.real_pt.review.repository.ptReview.PtReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class PtReviewServiceImpl implements PtReviewService{

    private final PtReviewRepository ptReviewRepository;

    public PtReviewServiceImpl(PtReviewRepository ptReviewRepository) {
        this.ptReviewRepository = ptReviewRepository;
    }

    @Override
    public Long saveReview(PtReviewReqDto reqDto) {
        return null;
    }

    @Override
    public PtReviewResDto updateReview(PtReviewReqDto updDto) {
        return null;
    }

    @Override
    public List<PtReviewResDto> findAllReviews(Long gymId, Long ptId) {
        return null;
    }

    @Override
    public List<PtReviewResDto> findAllSortedReviews(Long gymId, Long ptId) {
        return null;
    }

    @Override
    public void deleteReview(Long id) {

    }

    @Override
    public Long addLike(Long id) {
        return null;
    }

    @Override
    public Long subLike(Long id) {
        return null;
    }

    @Override
    public Long addHate(Long id) {
        return null;
    }

    @Override
    public Long subHate(Long id) {
        return null;
    }
}
