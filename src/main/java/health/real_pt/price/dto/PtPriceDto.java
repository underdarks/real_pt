package health.real_pt.price.dto;

import health.real_pt.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class PtPriceDto {

    private Long id;
    private Member trainer;    //트레이너
    private Long regularPrice;  //정상가
    private Long discountPrice; //할인가
    private Integer times;  //횟수 (ex. 10회, 20회, 30회 등..)}


    public PtPriceDto(Long id, Member trainer, Long regularPrice, Long discountPrice, Integer times) {
        this.id = id;
        this.trainer = trainer;
        this.regularPrice = regularPrice;
        this.discountPrice = discountPrice;
        this.times = times;
    }
}
