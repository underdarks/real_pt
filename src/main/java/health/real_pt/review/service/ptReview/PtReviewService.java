package health.real_pt.review.service.ptReview;

import health.real_pt.common.BaseInterface;
import health.real_pt.review.domain.PtReview;
import health.real_pt.review.dto.ptReview.PtReviewReqDto;
import health.real_pt.review.dto.ptReview.PtReviewResDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PtReviewService extends BaseInterface<PtReview> {

    /**
     *              Notice
     *
     * 구현체 => PtReviewServiceImpl.java
     */

    /**
     * 리뷰 등록
     */
    Long saveReview(PtReviewReqDto reqDto, Long ptId, List<MultipartFile> files);

    /**
     * 리뷰 수정
     */
    PtReviewResDto updateReview(PtReviewReqDto updDto);

    /**
     * 리뷰 조회
     */
    List<PtReviewResDto> findReview(Long ptId);

    /**
     * 리뷰 조회 정렬(좋아요 많은 순, 좋아요 적은 순, 싫어요 많은 순, 싫어요 적은 순, 최신 순, 오래된 순, 평점 높은 순 등...)
     */
    List<PtReviewResDto> findAllSortedReviews(Long gymId, Long ptId, String sortType);

    /**
     * 리뷰 삭제
     */
    void deleteReview(Long id);

    /**
     * 도움이 돼요(좋아요) 증가 (+1)
     */
    Long addGood(Long id);

    /**
     * 도움이 돼요(좋아요) 감소 (-1)
     */
    Long subGood(Long id);

    /**
     * 도움이 안돼요(싫아요) 증가 (+1)
     */
    Long addBad(Long id);

    /**
     * 도움이 안돼요(싫아요) 감소 (-1)
     */
    Long subBad(Long id);
}
