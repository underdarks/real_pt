package health.real_pt.gym.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymReqResDto;

import java.util.List;

public interface GymService {

    /**
     *              Notice
     *
     * 구현체 => GymServiceImpl.java
     */

    /**
     * 헬스장 정보 등록
     */
    Long saveGym(GymReqResDto gymReqDto);

    /**
     * 헬스장 정보 수정
     */
    void updateGym(Long id, GymReqResDto GymReqDto);


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
    Gym findOne(Long id);


    /**
     * 헬스장 정보 삭제
     * @param
     */
    void deleteGym(Long id);



}
