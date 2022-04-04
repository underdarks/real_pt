package health.real_pt.price.api.GymPrice;

import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.dto.GymPrice.GymPriceDto;
import health.real_pt.price.service.GymPrice.GymPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
     * @param gymPriceDto
     * @return
     */
    @PostMapping("")
    public Long saveGymPrice(@RequestHeader(value = "gym-id") Long gymId, @RequestBody @Valid GymPriceDto gymPriceDto) {
        return gymPriceService.saveGymPrice(gymPriceDto,gymId);
    }

    /**
     * 단일 가격 조회
     * @param gymId : 헬스장 ID
     * @param id    : 헬스장 가격 ID
     * @return
     */
    @GetMapping("/{id}")
    public GymPriceResDto findGymPrice(@RequestHeader(value = "gym-id") Long gymId, @PathVariable("id") Long id){
        return gymPriceService.findOnePrice(id);
    }

    /**
     * 전체 가격 조회
     * @param gymId : 헬스장 ID
     * @return
     */
    @GetMapping("")
    public GymPriceListDto findAllGymPrice(@RequestHeader(value = "gym-id") Long gymId){
        return gymPriceService.findAllPrice(gymId);
    }


    /**
     * 가격 수정
     * @return : PK
     */
    @PatchMapping("/{id}")
    public GymPriceResDto updateGymPrice(@PathVariable("id") Long id, @RequestBody @Valid GymPriceDto gymPriceDto){
        gymPriceDto.setId(id);
        return gymPriceService.updateGymPrice(gymPriceDto);
    }

    /**
     * 가격 삭제
     * @return : PK
     */
    @DeleteMapping("/{id}")
    public String deleteGymPrice(@PathVariable("id") Long id){
        gymPriceService.deleteGymPrice(id);

        return "success";
    }

}
