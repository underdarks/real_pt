package health.real_pt.price.repository.ptPrice;

import health.real_pt.price.domain.PtPrice;

import java.util.List;
import java.util.Optional;

public interface PtPriceRepository {
    /**
     *
     * 구현체 => MySqlPtPriceRepository.java
     */

    Long save(PtPrice ptPrice);

    List<PtPrice> findAll();

    Optional<PtPrice> findById(Long id);

    void delete(PtPrice ptPrice);
}
