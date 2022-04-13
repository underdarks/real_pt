package health.real_pt.gym.api;

import health.real_pt.common.response.CommonResponse;
import health.real_pt.common.response.CommonResMessage;
import health.real_pt.common.response.StatusCode;
import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymReqResDto;
import health.real_pt.gym.dto.GymListDto;
import health.real_pt.gym.dto.GymResResDto;
import health.real_pt.gym.service.GymService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CommonResponse> saveGym(@RequestBody @Valid GymReqResDto reqGymReqDto){
        gymService.saveGym(reqGymReqDto);

        return new ResponseEntity(
                CommonResponse.createResponse(StatusCode.CREATED, CommonResMessage.CREATED_GYM_SUCCESS),
                HttpStatus.CREATED
        );
    }


    /**
     * 헬스장 정보 수정
     * @param id        : 수정할 ID(PK)
     * @param updGymReqDto : 수정할 헬스장 정보 DTO
     * @return          : 수정된 DTO
     */
    @ApiOperation(value = "헬스장 수정", notes = "id를 받아 헬스장 정보를 수정합니다.")
    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse> updateGym(@PathVariable("id") Long id, @RequestBody @Valid GymReqResDto updGymReqDto){
        gymService.updateGym(id, updGymReqDto);
        GymResResDto resDto = new GymResResDto().entityToDto(gymService.findOne(id));

        return new ResponseEntity(
                CommonResponse.createResponse(StatusCode.OK, CommonResMessage.UPDATE_GYM_SUCCESS,resDto),
                        HttpStatus.OK
        );
    }

    /**
     * 단일 헬스장 조회
     * @param id : 헬스장 ID(PK)
     * @return   : GymDTO
     */
    @ApiOperation(value = "단일 헬스장 조회", notes = "id를 받아 헬스장 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> findGym(@PathVariable("id") Long id){
        GymResResDto resDto = new GymResResDto().entityToDto(gymService.findOne(id));

        return new ResponseEntity(
                CommonResponse.createResponse(StatusCode.OK,CommonResMessage.READ_GYM_SUCCESS,resDto),
                HttpStatus.OK
        );
    }

    /**
     * 모든 헬스장 조회
     * @return :GymResultDTO
     */
    @ApiOperation(value = "전체 헬스장 조회", notes = "모든 헬스장 정보를 조회합니다.")
    @GetMapping("")
    public ResponseEntity<CommonResponse> findAllGym(){
        List<Gym> findGyms = gymService.findGyms();

        //Entity List -> Dto List
        List<GymReqResDto> gymReqDtoList = findGyms.stream()
                .map(gym -> new GymReqResDto().entityToDto(gym))
                .collect(Collectors.toList());

        GymListDto gymListDto = new GymListDto(gymReqDtoList.size(), gymReqDtoList);

        return new ResponseEntity(
                CommonResponse.createResponse(StatusCode.OK,CommonResMessage.READ_ALL_GYM_SUCCESS,gymListDto),
                HttpStatus.OK
        );

    }


    /**
     *
     * 헬스장 삭제
     * @return 성공 여부
     */
    @ApiOperation(value = "헬스장 삭제", notes = "id를 받아 헬스장 정보를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteGym(@PathVariable("id") Long id){
        gymService.deleteGym(id);

        return new ResponseEntity(
                CommonResponse.createResponse(StatusCode.OK, CommonResMessage.DELETE_GYM_SUCCESS),
                HttpStatus.OK
        );
    }


}
