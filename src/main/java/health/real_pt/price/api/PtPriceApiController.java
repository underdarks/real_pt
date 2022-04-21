package health.real_pt.price.api;

import health.real_pt.common.response.CommonResMessage;
import health.real_pt.common.response.CommonResEntity;
import health.real_pt.common.response.StatusCode;
import health.real_pt.price.dto.ptPrice.PtPriceListDto;
import health.real_pt.price.dto.ptPrice.PtPriceReqDto;
import health.real_pt.price.dto.ptPrice.PtPriceResDto;
import health.real_pt.price.service.ptPrice.PtPriceService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Pt 가격(PtPrice) API Controller
 * Member - PtPrice(1:N 관계)
 */
@RestController
@RequestMapping("api/v1/pt/{pt-id}/price")
@RequiredArgsConstructor
public class PtPriceApiController {

    private final PtPriceService ptPriceService;

    /**
     * 가격 등록
     * @param ptId : memberID
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "PT 가격 등록", notes = "PT 가격 정보를 등록합니다.")
    @PostMapping("")
    public ResponseEntity<CommonResEntity> savePtPrice(@PathVariable(value = "pt-id") Long ptId, @RequestBody @Valid PtPriceReqDto reqDto) {
        ptPriceService.savePrice(reqDto, ptId);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.CREATED, CommonResMessage.CREATED_PT_PRICE_SUCCESS),
                HttpStatus.CREATED
        );
    }


    /**
     * 가격 수정
     * @param id     : ptPriceID
     * @param reqDto : 수정 내용
     * @return
     */
    @ApiOperation(value = "PT 가격 수정", notes = "id를 받아 가격 정보를 수정합니다.")
    @PatchMapping("{id}")
    public ResponseEntity<CommonResEntity>  updatePtPrice(@PathVariable(value = "id") Long id, @RequestBody @Valid PtPriceReqDto reqDto){
        reqDto.setId(id);
        PtPriceResDto resDto = ptPriceService.updatePrice(reqDto);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.UPDATE_PT_PRICE_SUCCESS,resDto),
                HttpStatus.OK
        );
    }

    /**
     * 단일 가격 조회
     * @return
     */
    @ApiOperation(value = "PT 가격 조회", notes = "id를 받아 가격 정보를 조회합니다.")
    @GetMapping("{id}")
    public ResponseEntity<CommonResEntity>  findPtPrice(@PathVariable(value = "id") Long id){
        PtPriceResDto resDto = ptPriceService.findOnePrice(id);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.READ_PT_PRICE_SUCCESS,resDto),
                HttpStatus.OK
        );
    }

    /**
     * 모든 가격 조회
     * @return
     */
    @ApiOperation(value = "PT 가격 전체 조회", notes = "해당 pt의 모든 가격 정보를 조회합니다.")
    @GetMapping()
    public ResponseEntity<CommonResEntity>  findAllPtPrice(@PathVariable(value = "pt-id") Long ptId){
        List<PtPriceResDto> resDtoList = ptPriceService.findAllPrice(ptId);
        PtPriceListDto listDto = new PtPriceListDto(resDtoList, resDtoList.size());

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.READ_ALL_GYM_PRICE_SUCCESS,listDto),
                HttpStatus.OK
        );
    }

    /**
     * 가격 삭제
     * @return
     */
    @ApiOperation(value = "PT 가격 삭제", notes = "id를 받아 가격 정보를 삭제합니다.")
    @DeleteMapping("{id}")
    public ResponseEntity<CommonResEntity>  deletePtPrice(@PathVariable(value = "id") Long id){
        ptPriceService.deletePrice(id);
        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.DELETE_PT_PRICE_SUCCESS),
                HttpStatus.OK
        );
    }


}
