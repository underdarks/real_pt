package health.real_pt.price.api;

import health.real_pt.common.response.CommonResMessage;
import health.real_pt.common.response.CommonResEntity;
import health.real_pt.common.response.StatusCode;
import health.real_pt.exception.exception_handler.ExceptionType;
import health.real_pt.exception.exceptions.CommonApiExceptions;
import health.real_pt.price.dto.gymPrice.GymPriceReqDto;
import health.real_pt.price.dto.gymPrice.GymPriceListDto;
import health.real_pt.price.dto.gymPrice.GymPriceResDto;
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
@RestController
@RequestMapping("api/v1/gym/{gym-id}/price")
@RequiredArgsConstructor
public class GymPriceApiController {

    private final GymPriceService gymPriceService;


    /**
     * 헬스장 가격 필수 값 확인 처리
     */
    public void checkReqDtoValidation(GymPriceReqDto reqDto) {
        if (reqDto.getRegularPrice() == null || reqDto.getRegularPrice() < 0)
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL, "regularPrice는 필수 값입니다.");

        else if (reqDto.getDiscountPrice() == null || reqDto.getDiscountPrice() < 0)
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL, "discountPrice는 필수 값입니다.");

        else if (reqDto.getMonths() == null || reqDto.getMonths().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL, "months는 필수 값입니다.");

    }

    /**
     * 가격 등록
     *
     * @param gymId          : 헬스장 ID
     * @param reqDto
     */
    @ApiOperation(value = "헬스장 가격 등록", notes = "가격을 등록합니다.")
    @PostMapping("")
    public ResponseEntity<CommonResEntity> saveGymPrice(@PathVariable(value = "gym-id") Long gymId, @RequestBody @Valid GymPriceReqDto reqDto) {
        checkReqDtoValidation(reqDto);
        gymPriceService.savePrice(reqDto, gymId);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.CREATED, CommonResMessage.CREATED_GYM_PRICE_SUCCESS),
                HttpStatus.CREATED
        );
    }

    /**
     * 단일 가격 조회
     *
     * @param gymId : 헬스장 ID
     * @param id    : 헬스장 가격 ID
     */
    @ApiOperation(value = "헬스장 가격 조회", notes = "id를 받아 가격을 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResEntity> findGymPrice(@PathVariable(value = "gym-id") Long gymId, @PathVariable("id") Long id) {
        GymPriceResDto resDto = gymPriceService.findOnePrice(id);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.CREATED, CommonResMessage.READ_GYM_PRICE_SUCCESS, resDto),
                HttpStatus.CREATED
        );
    }

    /**
     * 전체 가격 조회
     *
     * @param gymId : 헬스장 ID
     */
    @ApiOperation(value = "헬스장 가격 전체 조회", notes = "특정 헬스장 가격을 전체 조회합니다.")
    @GetMapping("")
    public ResponseEntity<CommonResEntity> findAllGymPrice(@PathVariable(value = "gym-id") Long gymId) {
        List<GymPriceResDto> resDtoList = gymPriceService.findAllPrice(gymId);
        GymPriceListDto priceListDto = new GymPriceListDto(resDtoList.size(), resDtoList);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.CREATED, CommonResMessage.READ_ALL_GYM_PRICE_SUCCESS, priceListDto),
                HttpStatus.CREATED
        );
    }


    /**
     * 가격 수정
     */
    @ApiOperation(value = "헬스장 가격 수정", notes = "id를 받아 헬스장 가격을 수정합니다.")
    @PatchMapping("/{id}")
    public ResponseEntity<CommonResEntity> updateGymPrice(@PathVariable("id") Long id, @RequestBody @Valid GymPriceReqDto gymPriceReqDto) {
        gymPriceReqDto.setId(id);
        GymPriceResDto resDto = gymPriceService.updatePrice(gymPriceReqDto);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.CREATED, CommonResMessage.UPDATE_GYM_PRICE_SUCCESS, resDto),
                HttpStatus.CREATED
        );
    }

    /**
     * 가격 삭제
     */
    @ApiOperation(value = "헬스장 가격 삭제", notes = "id를 받아 헬스장 가격을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResEntity> deleteGymPrice(@PathVariable("id") Long id) {
        gymPriceService.deletePrice(id);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.CREATED, CommonResMessage.DELETE_GYM_PRICE_SUCCESS),
                HttpStatus.OK
        );
    }

}
