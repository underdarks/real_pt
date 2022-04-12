package health.real_pt.common;

import org.springframework.transaction.annotation.Transactional;


public interface BaseInterface<T> {
    /**
     * Notice
     *
     * Service Layer 공통 인터페이스
     */

    T findEntity(Long id);
}
