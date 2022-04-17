package health.real_pt.image.repository;

import health.real_pt.image.domain.PtReviewFile;

import java.util.List;

public interface PtReviewFileRepository {

    //등록
    Long save(PtReviewFile file);

    //조회
    List<PtReviewFile> findById(Long prId);
}
