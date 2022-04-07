package health.real_pt.price.api.gymPrice;

import health.real_pt.price.dto.gymPrice.GymPriceReqDto;
import health.real_pt.price.dto.gymPrice.GymPriceListDto;
import health.real_pt.price.dto.gymPrice.GymPriceResDto;
import health.real_pt.price.service.gymPrice.GymPriceService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
     * @return
     */
    @ApiOperation(value = "헬스장 가격 등록", notes = "가격을 등록합니다." )
    @PostMapping("")
    public Long saveGymPrice(@RequestHeader(value = "gym-id") Long gymId, @RequestBody @Valid GymPriceReqDto gymPriceReqDto) {
        return gymPriceService.savePrice(gymPriceReqDto,gymId);
    }

    /**
     * 단일 가격 조회
     * @param gymId : 헬스장 ID
     * @param id    : 헬스장 가격 ID
     * @return
     */
    @ApiOperation(value = "헬스장 가격 조회", notes = "id를 받아 가격을 조회합니다." )
    @GetMapping("/{id}")
    public GymPriceResDto findGymPrice(@RequestHeader(value = "gym-id") Long gymId, @PathVariable("id") Long id){
        return gymPriceService.findOnePrice(id);
    }

    /**
     * 전체 가격 조회
     * @param gymId : 헬스장 ID
     * @return
     */
    @ApiOperation(value = "헬스장 가격 전체 조회",notes = "특정 헬스장 가격을 전체 조회합니다.")
    @GetMapping("")
    public GymPriceListDto findAllGymPrice(@RequestHeader(value = "gym-id") Long gymId){
        List<GymPriceResDto> resDtoList = gymPriceService.findAllPrice(gymId);

        return new GymPriceListDto(resDtoList.size(),resDtoList);
    }


    /**
     * 가격 수정
     * @return : PK
     */
    @ApiOperation(value = "헬스장 가격 수정", notes = "id를 받아 헬스장 가격을 수정합니다.")
    @PatchMapping("/{id}")
    public GymPriceResDto updateGymPrice(@PathVariable("id") Long id, @RequestBody @Valid GymPriceReqDto gymPriceReqDto){
        gymPriceReqDto.setId(id);
        return gymPriceService.updatePrice(gymPriceReqDto);
    }

    /**
     * 가격 삭제
     * @return : PK
     */
    @ApiOperation(value = "헬스장 가격 삭제",notes = "id를 받아 헬스장 가격을 삭제합니다.")
    @DeleteMapping("/{id}")
    public String deleteGymPrice(@PathVariable("id") Long id){
        gymPriceService.deletePrice(id);
        return "success";
    }

}
