package health.real_pt.image.repository;

import health.real_pt.image.domain.PtReviewImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MysqlPtReviewFileRepository implements PtReviewFileRepository {

    private final EntityManager em;

    //등록
    @Override
    public Long save(PtReviewImage file){
        em.persist(file);
        return file.getId();
    }

    //조회
    @Override
    public List<PtReviewImage> findById(Long prId){
        return em.createQuery(
                "select pf from PtReviewFile pf " +
                        "join pf.ptReview pr " +
                        "where pr.id =:prId", PtReviewImage.class)
                .setParameter("prId",prId)
                .getResultList();
    }

    //삭제

}
