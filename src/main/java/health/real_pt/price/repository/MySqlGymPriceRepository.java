package health.real_pt.price.repository;

import health.real_pt.price.domain.GymPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
    public List<GymPrice> findAll() {
        return em.createQuery("select gp from GymPrice gp", GymPrice.class).getResultList();    //결과를 컬렉션으로 반환. 결과가 없으면 빈 컬렉션 반환
    }

    @Override
    public Optional<GymPrice> findById(Long id) {
        GymPrice gymPrice = em.find(GymPrice.class, id);
        return Optional.ofNullable(gymPrice);
    }

    @Override
    public void delete(GymPrice gymPrice) {
        em.remove(gymPrice);
    }
}
