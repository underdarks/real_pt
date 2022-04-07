package health.real_pt.review.repository.ptReview;

import health.real_pt.price.domain.PtPrice;
import health.real_pt.review.domain.PtReview;

import java.util.List;
import java.util.Optional;

public interface PtReviewRepository {
    /**
     *
     * 구현체 => MySqlPtReviewRepository.java
     */

    /**
     * pt 리뷰 등록
     */
    Long save(PtReview ptReview);


    /**
     * pt 리뷰 조회( 헬스장 - 트레이너 - 리뷰)
     */
    List<PtReview> findAll(Long gymId,Long ptId,String orderType);


    /**
     * 리뷰 삭제
     */
    void delete(PtReview ptReview);





}
