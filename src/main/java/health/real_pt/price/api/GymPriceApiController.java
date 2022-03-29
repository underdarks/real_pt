package health.real_pt.price.api;

import health.real_pt.price.dto.GymPriceDto;
import health.real_pt.price.service.GymPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/gymprice")
@RequiredArgsConstructor
public class GymPriceApiController {

    private final GymPriceService gymPriceService;

    @PostMapping("")
    public GymPriceDto saveGymPrice(GymPriceDto gymPriceDto){
        return null;
    }
}
