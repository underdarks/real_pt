package health.real_pt.gym.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymDto;

import java.util.List;
import java.util.Optional;

public interface GymService {

    /**
     *              Notice
     *
     * 구현체 => GymServiceImpl.java
     */

    /**
     * 헬스장 정보 등록
     */
    void saveGym(GymDto gymDto);

    /**
     * 헬스장 정보 수정
     */
    void updateGym(GymDto GymDto);


    /**
     * 헬스장 정보 전체 조회
     * @return -> List Gym Entity
     */
    List<Gym> findGyms();


    /**
     *
     * @param id -> PK
     * @return -> Gym Entity
     */
    Optional<Gym> findOne(Long id);


    /**
     * 헬스장 정보 삭제
     * @param
     */
    void deleteGym(GymDto gymDto);







}
