package health.real_pt.price.api.ptPrice;

import health.real_pt.price.dto.ptPrice.PtPriceListDto;
import health.real_pt.price.dto.ptPrice.PtPriceReqDto;
import health.real_pt.price.dto.ptPrice.PtPriceResDto;
import health.real_pt.price.service.ptPrice.PtPriceService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Pt 가격(PtPrice) API Controller
 * Member - PtPrice(1:N 관계)
 */
@RestController
@RequestMapping("api/v1/ptprice")
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
    public Long savePtPrice(@RequestHeader(value = "pt-id") Long ptId, @RequestBody @Valid PtPriceReqDto reqDto) {
        return ptPriceService.savePrice(reqDto, ptId);
    }


    /**
     * 가격 수정
     * @param id     : ptPriceID
     * @param reqDto : 수정 내용
     * @return
     */
    @ApiOperation(value = "PT 가격 수정", notes = "id를 받아 가격 정보를 수정합니다.")
    @PatchMapping("{id}")
    public PtPriceResDto updatePtPrice(@PathVariable(value = "id") Long id, @RequestBody @Valid PtPriceReqDto reqDto){
        reqDto.setId(id);
        return ptPriceService.updatePrice(reqDto);
    }

    /**
     * 단일 가격 조회
     * @return
     */
    @ApiOperation(value = "PT 가격 조회", notes = "id를 받아 가격 정보를 조회합니다.")
    @GetMapping("{id}")
    public PtPriceResDto findPtPrice(@PathVariable(value = "id") Long id){
        return ptPriceService.findOnePrice(id);
    }

    /**
     * 모든 가격 조회
     * @return
     */
    @ApiOperation(value = "PT 가격 전체 조회", notes = "모든 가격 정보를 조회합니다.")
    @GetMapping()
    public PtPriceListDto findAllPtPrice(@RequestHeader(value = "gym-id") Long gymId, @RequestHeader(value = "pt-id") Long ptId){
        List<PtPriceResDto> resDtoList = ptPriceService.findAllPrice(gymId, ptId);
        return new PtPriceListDto(resDtoList,resDtoList.size());
    }

    /**
     * 가격 삭제
     * @return
     */
    @ApiOperation(value = "PT 가격 삭제", notes = "id를 받아 가격 정보를 삭제합니다.")
    @DeleteMapping("{id}")
    public String deletePtPrice(@PathVariable(value = "id") Long id){
        ptPriceService.deletePrice(id);
        return "success";
    }


}
