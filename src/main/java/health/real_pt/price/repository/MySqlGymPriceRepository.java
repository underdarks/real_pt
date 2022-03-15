package health.real_pt.price.repository;

import health.real_pt.gym.domain.Gym;
import health.real_pt.price.domain.GymPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MySqlGymPriceRepository implements GymPriceRepository{

    private final EntityManager em;

    public MySqlGymPriceRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long save(GymPrice gymPrice) {
        em.persist(gymPrice);   //영속화
        return gymPrice.getId();
    }

    @Override
    public Optional<GymPrice> findById(Long id) {
        GymPrice gymPrice = em.find(GymPrice.class, id);
        return Optional.ofNullable(gymPrice);
    }

    @Override
    public List<GymPrice> findByGymId(Long gymId) {
//        List resultList = em.createQuery("select gp from GymPrice gp join gp.gym g" +
//                        " where g.id =:gym_id",GymPrice.class)
//                        .setParameter("gym_id", gymId)
//                        .getResultList();

        List resultList = em.createQuery("select gp from GymPrice gp gp.gym" +
                        " where g.id =:gym_id",GymPrice.class)
                .setParameter("gym_id", gymId)
                .getResultList();

        return resultList == null ? Collections.emptyList(): resultList;
    }

    @Override
    public void delete(GymPrice gymPrice) {
        em.remove(gymPrice);
    }
}
