package health.real_pt.price.dto.ptPrice;

import health.real_pt.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PtPriceReqDto {

    private Long id;
    private Member pt;          //트레이너
    private Long regularPrice;  //정상가
    private Long discountPrice; //할인가
    private String times;       //횟수 (ex. 10회, 20회, 30회 혹은 OT 2회 무료 등..)}

}

