package health.real_pt.gym.service;

import health.real_pt.common.BaseInterface;
import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymReqDto;
import health.real_pt.gym.dto.GymResDto;
import health.real_pt.gym.dto.PtResDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GymService extends BaseInterface<Gym> {

    /**
     *              Notice
     *
     * 구현체 => GymServiceImpl.java
     */

    /**
     * 헬스장 정보 등록
     */
    Long saveGym(GymReqDto gymReqDto, List<MultipartFile> files);

    /**
     * 헬스장 정보 수정
     */
    GymResDto updateGym(Long id, GymReqDto GymReqDto, List<MultipartFile> files);


    /**
     * 헬스장 정보 전체 조회
     * @return -> List Gym Entity
     */
    List<GymResDto> findGyms();


    /**
     *
     * @param id -> PK
     * @return -> Gym Entity
     */
    GymResDto findOne(Long id);

    /**
     * 헬스장 소속 PT 찾기
     */
    List<PtResDto> findPtBelongGym(Long id);


    /**
     * 헬스장 정보 삭제
     * @param
     */
    void deleteGym(Long id);



}
