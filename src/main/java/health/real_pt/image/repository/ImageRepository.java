package health.real_pt.image.repository;

import health.real_pt.image.domain.PtReviewImage;

import java.util.List;


public interface ImageRepository<T> {
    /**
     * 이미지 공통 Respository 인터페이스
     */

    //등록
    Long save(T file);

    //조회
    List<T> findById(Long id);

    //삭제
    void delete(T entity);
}
