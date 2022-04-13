package health.real_pt.price.api;

import health.real_pt.common.response.CommonResMessage;
import health.real_pt.common.response.CommonResponse;
import health.real_pt.common.response.StatusCode;
import health.real_pt.price.dto.gymPrice.GymPriceReqResDto;
import health.real_pt.price.dto.gymPrice.GymPriceListDto;
import health.real_pt.price.dto.gymPrice.GymPriceResResDto;
import health.real_pt.price.service.gymPrice.GymPriceService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 헬스장 가격(GYmPrice) API Controller
 * Gym - GymPrice(1:N 관계)
 */
@RestController @RequestMapping("api/v1/gymprice")
@RequiredArgsConstructor
public class GymPriceApiController {

    private final GymPriceService gymPriceService;

    /**
     * 가격 등록
     * @param gymId : 헬스장 ID
     * @param gymPriceReqDto
     */
    @ApiOperation(value = "헬스장 가격 등록", notes = "가격을 등록합니다." )
    @PostMapping("")
    public ResponseEntity<CommonResponse> saveGymPrice(@RequestHeader(value = "gym-id") Long gymId, @RequestBody @Valid GymPriceReqResDto gymPriceReqDto) {
        gymPriceService.savePrice(gymPriceReqDto, gymId);
        return new ResponseEntity(
                CommonResponse.createResponse(StatusCode.CREATED, CommonResMessage.CREATED_GYM_PRICE_SUCCESS),
                HttpStatus.CREATED
        );
    }

    /**
     * 단일 가격 조회
     * @param gymId : 헬스장 ID
     * @param id    : 헬스장 가격 ID
     */
    @ApiOperation(value = "헬스장 가격 조회", notes = "id를 받아 가격을 조회합니다." )
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> findGymPrice(@RequestHeader(value = "gym-id") Long gymId, @PathVariable("id") Long id){
        GymPriceResResDto resDto = gymPriceService.findOnePrice(id);

        return new ResponseEntity(
                CommonResponse.createResponse(StatusCode.CREATED, CommonResMessage.READ_GYM_PRICE_SUCCESS,resDto),
                HttpStatus.CREATED
        );
    }

    /**
     * 전체 가격 조회
     * @param gymId : 헬스장 ID
     */
    @ApiOperation(value = "헬스장 가격 전체 조회",notes = "특정 헬스장 가격을 전체 조회합니다.")
    @GetMapping("")
    public ResponseEntity<CommonResponse> findAllGymPrice(@RequestHeader(value = "gym-id") Long gymId){
        List<GymPriceResResDto> resDtoList = gymPriceService.findAllPrice(gymId);
        GymPriceListDto priceListDto = new GymPriceListDto(resDtoList.size(), resDtoList);

        return new ResponseEntity(
                CommonResponse.createResponse(StatusCode.CREATED, CommonResMessage.READ_ALL_GYM_PRICE_SUCCESS,priceListDto),
                HttpStatus.CREATED
        );
    }


    /**
     * 가격 수정
     */
    @ApiOperation(value = "헬스장 가격 수정", notes = "id를 받아 헬스장 가격을 수정합니다.")
    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse> updateGymPrice(@PathVariable("id") Long id, @RequestBody @Valid GymPriceReqResDto gymPriceReqDto){
        gymPriceReqDto.setId(id);
        GymPriceResResDto resDto = gymPriceService.updatePrice(gymPriceReqDto);

        return new ResponseEntity(
                CommonResponse.createResponse(StatusCode.CREATED, CommonResMessage.UPDATE_GYM_PRICE_SUCCESS,resDto),
                HttpStatus.CREATED
        );
    }

    /**
     * 가격 삭제
     */
    @ApiOperation(value = "헬스장 가격 삭제",notes = "id를 받아 헬스장 가격을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteGymPrice(@PathVariable("id") Long id){
        gymPriceService.deletePrice(id);

        return new ResponseEntity(
                CommonResponse.createResponse(StatusCode.CREATED, CommonResMessage.DELETE_GYM_PRICE_SUCCESS),
                HttpStatus.OK
        );
    }

}
