package health.real_pt.price.api;

import health.real_pt.gym.service.GymService;
import health.real_pt.price.dto.GymPriceDto;
import health.real_pt.price.service.GymPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpHeaders;

@RestController
@RequestMapping("api/v1/gymprice")
@RequiredArgsConstructor
public class GymPriceApiController {

    private final GymPriceService gymPriceService;
    private final GymService gymService;

    @PostMapping("")
    public GymPriceDto saveGymPrice(
            @RequestHeader HttpHeaders requestHeader,
            @RequestBody @Valid GymPriceDto gymPriceDto) {

        gymPriceDto.
        gymPriceService.saveGymPrice(gymPriceDto,)

    }
}
