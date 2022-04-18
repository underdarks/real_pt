package health.real_pt.image.repository;

import health.real_pt.image.domain.PtReviewImage;

import java.util.List;

public interface PtReviewFileRepository {

    //등록
    Long save(PtReviewImage file);

    //조회
    List<PtReviewImage> findById(Long prId);
}
