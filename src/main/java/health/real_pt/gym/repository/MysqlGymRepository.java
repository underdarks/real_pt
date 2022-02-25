package health.real_pt.gym.repository;

import health.real_pt.gym.domain.Gym;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
//@RequiredArgsConstructor
public class MysqlGymRepository implements GymRepository{

    private final EntityManager em;

    public MysqlGymRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long save(Gym gym) {
        em.persist(gym);
        return gym.getId();
    }

    @Override
    public Optional<Gym> findById(Long id) {
        Gym gym = em.find(Gym.class, id);
        return Optional.ofNullable(gym);
    }

    @Override
    public List<Gym> findAll() {
        List<Gym> gyms = em.createQuery("select g from Gym g", Gym.class).getResultList();
        return gyms != null ? gyms: Collections.emptyList();
    }

    @Override
    public void delete(Gym gym) {
        em.remove(gym);
    }
}
