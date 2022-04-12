package health.real_pt.gym.api;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymReqDto;
import health.real_pt.gym.dto.GymResDto;
import health.real_pt.gym.service.GymService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/gym")
@RequiredArgsConstructor
public class GymApiController {

    private final GymService gymService;

    /**
     * 헬스장 정보 저장
     * @param reqGymReqDto : 저장할 헬스장 정보 DTO
     * @return : ID(PK)값
     */
    @ApiOperation(value = "헬스장 등록", notes = "헬스장 정보를 등록합니다.")
    @PostMapping("")
    public ResponseEntity saveGym(@RequestBody @Valid GymReqDto reqGymReqDto){
        return gymService.saveGym(reqGymReqDto);
    }


    /**
     * 헬스장 정보 수정
     * @param id        : 수정할 ID(PK)
     * @param updGymReqDto : 수정할 헬스장 정보 DTO
     * @return          : 수정된 DTO
     */
    @ApiOperation(value = "헬스장 수정", notes = "id를 받아 헬스장 정보를 수정합니다.")
    @PatchMapping("/{id}")
    public ResponseEntity updateGym(@PathVariable("id") Long id, @RequestBody @Valid GymReqDto updGymReqDto){
        gymService.updateGym(id, updGymReqDto);
        Gym gym = gymService.findOne(id);

        return new GymReqDto().entityToDto(gym);
    }

    /**
     * 단일 헬스장 조회
     * @param id : 헬스장 ID(PK)
     * @return   : GymDTO
     */
    @ApiOperation(value = "단일 헬스장 조회", notes = "id를 받아 헬스장 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity findGym(@PathVariable("id") Long id){
        Gym gym = gymService.findOne(id);

        return new GymReqDto().entityToDto(gym);
    }

    /**
     * 모든 헬스장 조회
     * @return :GymResultDTO
     */
    @ApiOperation(value = "전체 헬스장 조회", notes = "모든 헬스장 정보를 조회합니다.")
    @GetMapping("")
    public ResponseEntity findAllGym(){
        List<Gym> findGyms = gymService.findGyms();

        //Entity List -> Dto List
        List<GymReqDto> gymReqDtoList = findGyms.stream()
                .map(gym -> new GymReqDto().entityToDto(gym))
                .collect(Collectors.toList());


        return new GymResDto(gymReqDtoList.size(), gymReqDtoList);
    }


    /**
     *
     * 헬스장 삭제
     * @return 성공 여부
     */
    @ApiOperation(value = "헬스장 삭제", notes = "id를 받아 헬스장 정보를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteGym(@PathVariable("id") Long id){
        gymService.deleteGym(id);

        return "success";
    }


}
