package health.real_pt.gym.repository;

import health.real_pt.gym.domain.Gym;

import java.util.List;
import java.util.Optional;

public interface GymRepository {
    /**
     *              Notice
     *     - 인터페이스 규칙 -
     *     public abstract 메서드
     *     public static final 변수
     *     생략 가능
     *
     *     구현체 => MysqlGymRepository.java
     */

    //등록, 수정
    Long save(Gym gym);

    //단일 조회
    Optional<Gym> findById(Long id);

    //전체 조회
    List<Gym> findAll();

    //삭제
    void delete(Gym gym);

}
