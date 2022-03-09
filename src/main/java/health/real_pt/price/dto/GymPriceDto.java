package health.real_pt.price.dto;

import health.real_pt.gym.domain.Gym;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class GymPriceDto {

    private Long id;
    private Gym gym;    //GYM PK
    private Long regularPrice;  //정상가
    private Long discountPrice; //할인가
    private Integer months;  //월수 (ex. 1개월, 3개월, 6개월 등)


    public GymPriceDto(Long id, Gym gym, Long regularPrice, Long discountPrice, Integer months) {
        this.id = id;
        this.gym = gym;
        this.regularPrice = regularPrice;
        this.discountPrice = discountPrice;
        this.months = months;
    }
}
