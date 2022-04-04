package health.real_pt.price.repository;

import health.real_pt.price.domain.GymPrice;

import java.util.List;
import java.util.Optional;

public interface GymPriceRepository {

    /**
     *
     * 구현체 => MySqlGymPriceRepository.java
     */

    /**
     * 저장
     * @param gymPrice
     * @return
     */
    Long save(GymPrice gymPrice);

    /**
     * gymPrice 조회
     * @param id : gymPriceID
     * @return
     */
    Optional<GymPrice> findById(Long id);

    /**
     * 해당 gym의 전체 가격들을 조회한다
     * @param gymId : gymID
     * @return
     */
    List<GymPrice> findByGymId(Long gymId);

    /**
     * 삭제
     * @param gymPrice
     */
    void delete(GymPrice gymPrice);
}
