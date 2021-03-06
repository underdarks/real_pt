package health.real_pt.price.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.member.domain.Member;
import health.real_pt.price.dto.ptPrice.PtPriceReqDto;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "PT_PRICE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "pt")
public class PtPrice extends BaseTimeEntity implements BaseEntity<PtPriceReqDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PT_PRICE_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)    //FK를 가지는 쪽이 Many, FetchType.Lazy로 설정하면 프록시 객체로 조회,GymPrice 엔티티만 DB에서 조회,
    @JoinColumn(name = "MEMBER_ID")     //Member PK
    private Member pt;          //Personal Trainer(개인 트레이너)

    @Column(name = "REGULAR_PRICE")
    private Long regularPrice;  //정상가

    @Column(name = "DISCOUNT_PRICE")
    private Long discountPrice; //할인가

    @Column(name = "TIMES")
    private String times;  //횟수 (ex. 10회, 20회, 30회 ... 혹은 OT 무료 x회)


    /**
     * setter 대신 도메인 객체 변경하는 메서드들(setter 사용 지양)
     */

    public void changeTrainer(Member trainer) {
        this.pt = trainer;
    }

    public void changeRegularPrice(Long regularPrice) {
        this.regularPrice = regularPrice;
    }

    public void changeDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void changeTimes(String times) {
        this.times = times;
    }


    // =========== 연관관계 편의 메서드

    private void addPtPrice(Member pt){
        this.pt=pt;
        this.pt.getPrices().add(this);
    }



    /* ============================================================================================================== */

    @Builder    //객체 생성(빌더 패턴)
    public PtPrice(Member pt, Long regularPrice, Long discountPrice, String times) {
        this.regularPrice = regularPrice;
        this.discountPrice = discountPrice;
        this.times = times;

        addPtPrice(pt);
    }

    public static PtPrice toEntity(PtPriceReqDto ptPriceReqDto) {
        return PtPrice.builder()
                .pt(ptPriceReqDto.getPt())
                .regularPrice(ptPriceReqDto.getRegularPrice())
                .discountPrice(ptPriceReqDto.getDiscountPrice())
                .times(ptPriceReqDto.getTimes())
                .build();
    }

    @Override
    public void updateEntity(PtPriceReqDto ptPriceReqDto) {
        if (ptPriceReqDto.getPt() != null)
            changeTrainer(ptPriceReqDto.getPt());

        if (ptPriceReqDto.getDiscountPrice() != null)
            changeDiscountPrice(ptPriceReqDto.getDiscountPrice());

        if (ptPriceReqDto.getRegularPrice() != null)
            changeRegularPrice(ptPriceReqDto.getRegularPrice());

        if (ptPriceReqDto.getTimes() != null)
            changeTimes(ptPriceReqDto.getTimes());
    }
}
