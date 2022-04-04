package health.real_pt.price.dto.GymPrice;

import health.real_pt.common.BaseDto;
import health.real_pt.gym.domain.Gym;
import health.real_pt.price.domain.GymPrice;
import lombok.Data;

@Data
public class GymPriceDto implements BaseDto<GymPrice,GymPriceDto> {

    private Long id;
    private Gym gym;    //GYM PK
    private Long regularPrice;  //정상가
    private Long discountPrice; //할인가
    private Integer months;  //월수 (ex. 1개월, 3개월, 6개월 등)


    @Override
    public GymPriceDto entityToDto(GymPrice gymPrice) {
        GymPriceDto gymPriceDto=new GymPriceDto();

        gymPriceDto.setId(gymPrice.getId());
        gymPriceDto.setGym(gymPrice.getGym());
        gymPriceDto.setRegularPrice(gymPrice.getRegularPrice());
        gymPriceDto.setDiscountPrice(gymPrice.getDiscountPrice());
        gymPriceDto.setMonths(gymPrice.getMonths());

        return gymPriceDto;
    }
}
