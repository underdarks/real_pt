package health.real_pt.image.repository;

import health.real_pt.image.domain.PtReviewFile;
import health.real_pt.price.domain.PtPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PtReviewFileRepository {

    private final EntityManager em;

    //등록
    public Long save(PtReviewFile file){
        em.persist(file);
        return file.getId();
    }

    //조회
    public List<PtReviewFile> findById(Long prId){
        return em.createQuery(
                "select pf from PtReviewFile pf " +
                        "join pf.ptReview pr " +
                        "where pr.id =:prId",PtReviewFile.class)
                .setParameter("prId",prId)
                .getResultList();
    }

    //삭제

}
