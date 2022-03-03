package health.real_pt.price.repository;

import health.real_pt.price.domain.GymPrice;

import java.util.List;
import java.util.Optional;

public interface GymPriceRepository {

    /**
     *
     * 구현체 => MySqlGymPriceRepository.java
     */

    Long save(GymPrice gymPrice);

    List<GymPrice> findAll();

    Optional<GymPrice> findById(Long id);

    void delete(GymPrice gymPrice);
}
