package health.real_pt.image.repository;

import health.real_pt.image.domain.GymImage;
import health.real_pt.image.domain.MemberImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GymImageRepository implements ImageRepository<GymImage> {

    private final EntityManager em;

    @Override
    public Long save(GymImage image) {
        em.persist(image);
        return image.getId();
    }

    @Override
    public List<GymImage> findById(Long id) {
        return null;
    }

    @Override
    public void delete(GymImage image) {
        em.remove(image);
    }
}
