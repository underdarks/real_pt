package health.real_pt.price.dto.ptPrice;

import health.real_pt.common.BaseDto;
import health.real_pt.price.domain.PtPrice;
import lombok.Data;

@Data
public class PtPriceResDto implements BaseDto<PtPrice,PtPriceResDto> {

    private Long id;
    private String trainerName; //트레이너 이름
    private Long regularPrice;  //정상가
    private Long discountPrice; //할인가
    private Integer times;  //횟수 (ex. 10회, 20회, 30회 등..)

    @Override
    public PtPriceResDto entityToDto(PtPrice ptPrice) {
        PtPriceResDto resDto = new PtPriceResDto();

        resDto.setTrainerName(ptPrice.getTrainer().getName());
        resDto.setRegularPrice(ptPrice.getRegularPrice());
        resDto.setDiscountPrice(ptPrice.getDiscountPrice());
        resDto.setTimes(ptPrice.getTimes());

        return resDto;
    }
}
