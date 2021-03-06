package health.real_pt.price.dto.gymPrice;

import health.real_pt.common.BaseDto;
import health.real_pt.gym.domain.Gym;
import health.real_pt.price.domain.GymPrice;
import lombok.Data;

@Data
public class GymPriceReqDto {

    private Long id;
    private Gym gym;            //헬스장
    private Long regularPrice;  //정상가
    private Long discountPrice; //할인가
    private String months;     //월수 (ex. 1개월, 3개월, 6개월 혹은 일일권..)


//    @Override
//    public GymPriceReqDto entityToDto(GymPrice gymPrice) {
//        GymPriceReqDto gymPriceReqDto =new GymPriceReqDto();
//
//        gymPriceReqDto.setId(gymPrice.getId());
//        gymPriceReqDto.setGym(gymPrice.getGym());
//        gymPriceReqDto.setRegularPrice(gymPrice.getRegularPrice());
//        gymPriceReqDto.setDiscountPrice(gymPrice.getDiscountPrice());
//        gymPriceReqDto.setMonths(gymPrice.getMonths());
//
//        return gymPriceReqDto;
//    }
}
