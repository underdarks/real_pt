package health.real_pt.gym.api;

import health.real_pt.common.response.CommonResEntity;
import health.real_pt.common.response.CommonResMessage;
import health.real_pt.common.response.StatusCode;
import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymReqDto;
import health.real_pt.gym.dto.GymListDto;
import health.real_pt.gym.dto.GymResDto;
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
    public ResponseEntity<CommonResEntity> saveGym(@RequestBody @Valid GymReqDto reqGymReqDto){
        gymService.saveGym(reqGymReqDto);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.CREATED, CommonResMessage.CREATED_GYM_SUCCESS),
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
    public ResponseEntity<CommonResEntity> updateGym(@PathVariable("id") Long id, @RequestBody @Valid GymReqDto updGymReqDto){
        gymService.updateGym(id, updGymReqDto);
        GymResDto resDto = new GymResDto().entityToDto(gymService.findOne(id));

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.UPDATE_GYM_SUCCESS,resDto),
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
    public ResponseEntity<CommonResEntity> findGym(@PathVariable("id") Long id){
        GymResDto resDto = new GymResDto().entityToDto(gymService.findOne(id));

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK,CommonResMessage.READ_GYM_SUCCESS,resDto),
                HttpStatus.OK
        );
    }

    /**
     * 모든 헬스장 조회
     * @return :GymResultDTO
     */
    @ApiOperation(value = "전체 헬스장 조회", notes = "모든 헬스장 정보를 조회합니다.")
    @GetMapping("")
    public ResponseEntity<CommonResEntity> findAllGym(){
        List<Gym> findGyms = gymService.findGyms();

        //Entity List -> Dto List
        List<GymReqDto> gymReqDtoList = findGyms.stream()
                .map(gym -> new GymReqDto().entityToDto(gym))
                .collect(Collectors.toList());

        GymListDto gymListDto = new GymListDto(gymReqDtoList.size(), gymReqDtoList);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK,CommonResMessage.READ_ALL_GYM_SUCCESS,gymListDto),
                HttpStatus.OK
        );

    }

    /**
     * 모든 헬스장 조회
     * @return :GymResultDTO
     */
    @ApiOperation(value = "헬스장 소속 pt 조회", notes = "툭정 헬스장에 속한 pt를 조회합니다.")
    @GetMapping("{/id}/pt")
    public ResponseEntity<CommonResEntity> findPtOfGym(){
        List<Gym> findGyms = gymService.findGyms();

        //Entity List -> Dto List
        List<GymReqDto> gymReqDtoList = findGyms.stream()
                .map(gym -> new GymReqDto().entityToDto(gym))
                .collect(Collectors.toList());

        GymListDto gymListDto = new GymListDto(gymReqDtoList.size(), gymReqDtoList);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK,CommonResMessage.READ_ALL_GYM_SUCCESS,gymListDto),
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
    public ResponseEntity<CommonResEntity> deleteGym(@PathVariable("id") Long id){
        gymService.deleteGym(id);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.DELETE_GYM_SUCCESS),
                HttpStatus.OK
        );
    }


}
