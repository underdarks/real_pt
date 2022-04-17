package health.real_pt.image.repository;

import health.real_pt.image.domain.PtReviewFile;
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
    public Long save(PtReviewFile file){
        em.persist(file);
        return file.getId();
    }

    //조회
    @Override
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
