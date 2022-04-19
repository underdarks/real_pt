package health.real_pt.image.repository;

import health.real_pt.image.domain.MemberImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberImageRepository implements ImageRepository<MemberImage> {

    private final EntityManager em;

    @Override
    public Long save(MemberImage image) {
        em.persist(image);
        return image.getId();
    }

    @Override
    public List<MemberImage> findById(Long memberId) {
        return null;
    }

    @Override
    public void delete(MemberImage memberImage) {
        em.remove(memberImage);
    }
}
