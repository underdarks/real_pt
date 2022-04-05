package health.real_pt.price.repository.ptPrice;

import health.real_pt.price.domain.PtPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class MySqlPtPriceRepository implements PtPriceRepository{

    private final EntityManager em;

    public MySqlPtPriceRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long save(PtPrice ptPrice) {
        em.persist(ptPrice);
        return ptPrice.getId();
    }

    @Override
    public List<PtPrice> findAll() {
        return em.createQuery("select pp from PtPrice pp",PtPrice.class).getResultList();
    }

    @Override
    public Optional<PtPrice> findById(Long id) {
        PtPrice ptPrice = em.find(PtPrice.class, id);
        return Optional.ofNullable(ptPrice);
    }

    @Override
    public void delete(PtPrice ptPrice) {
        em.remove(ptPrice);
    }

}
