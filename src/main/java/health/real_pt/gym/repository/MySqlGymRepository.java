package health.real_pt.gym.repository;

import health.real_pt.gym.domain.Gym;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
//@RequiredArgsConstructor
public class MySqlGymRepository implements GymRepository{

    private final EntityManager em;

    public MySqlGymRepository(EntityManager em) {
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
    public Optional<Gym> findByName(String name) {
        Gym gym = em.find(Gym.class, name);
        return Optional.ofNullable(gym);
    }

    @Override
    public List<Gym> findAll() {
        return em.createQuery("select g from Gym g", Gym.class).getResultList();    //결과를 컬렉션으로 반환. 결과가 없으면 빈 컬렉션 반환
    }

    @Override
    public void delete(Gym gym) {
        em.remove(gym);
    }
}
