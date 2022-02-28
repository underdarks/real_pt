package health.real_pt.gym.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.UpdateGymDto;

import java.util.List;
import java.util.Optional;

public interface GymService {

    /**
     * 헬스장 정보 등록
     */
    void saveGym(Gym gym);

    /**
     * 헬스장 정보 수정
     */
    void updateGym(UpdateGymDto updateGymDto);


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
     * @param id
     */
    void deleteGym(Long id);







}
