package health.real_pt.review.repository.ptReview;

import health.real_pt.review.domain.PtReview;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class MysqlPtReviewRepository implements PtReviewRepository{

    private final EntityManager em;

    public MysqlPtReviewRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Long save(PtReview ptReview) {
        em.persist(ptReview);
        return ptReview.getId();
    }

    @Override
    public List<PtReview> findAll(Long gymId, Long ptId, String orderType ) {

        /**
         * 좋아요 많은 순, 좋아요 적은 순, 싫어요 많은 순, 싫어요 적은 순, 최신 순, 오래된 순, 평점 높은 순
         */
        String sql="select pr from PtReview pr " +
                "join pr.pt m "+
                "join m.gym g "+
                "where m.id =: ptId and g.id =: gymId " +
                "order by pr." + orderType;

        return em.createQuery(sql).getResultList();
    }

    @Override
    public Optional<PtReview> findById(Long id) {
        return Optional.ofNullable(em.find(PtReview.class, id));
    }

    @Override
    public void delete(PtReview ptReview) {
        em.remove(ptReview);
    }
}
