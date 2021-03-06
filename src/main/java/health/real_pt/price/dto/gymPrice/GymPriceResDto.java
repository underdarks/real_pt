package health.real_pt.price.dto.gymPrice;

import health.real_pt.common.BaseDto;
import health.real_pt.price.domain.GymPrice;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GymPrice 응답(RES) DTO
 */
@Data
@NoArgsConstructor
public class GymPriceResDto implements BaseDto<GymPrice, GymPriceResDto> {

    private Long id;
    private String name;        //헬스장 이름
    private Long regularPrice;  //정상가
    private Long discountPrice; //할인가
    private String months;  //월수 (ex. 1개월, 3개월, 6개월 등)


    @Override
    public GymPriceResDto entityToDto(GymPrice gymPrice) {
        GymPriceResDto resDto = new GymPriceResDto();

        resDto.setId(gymPrice.getId());
        resDto.setName(gymPrice.getGym().getName());
        resDto.setRegularPrice(gymPrice.getRegularPrice());
        resDto.setDiscountPrice(gymPrice.getDiscountPrice());
        resDto.setMonths(gymPrice.getMonths());

        return resDto;
    }
}
