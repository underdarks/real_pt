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
     * 리뷰 등록
     */
    Long save(PtReview ptReview);


    /**
     * 리뷰 조회(트레이너 - 리뷰)
     */
    List<PtReview> findAll(Long ptId,String orderType);

    /**
     * 리뷰 조회
     * @param id : PtReview PK
     */
    Optional<PtReview> findById(Long id);


    /**
     * 리뷰 삭제
     */
    void delete(PtReview ptReview);





}
