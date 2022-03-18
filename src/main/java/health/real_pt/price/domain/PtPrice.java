package health.real_pt.price.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.member.domain.Member;
import health.real_pt.price.dto.PtPriceDto;
import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "PT_PRICE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class PtPrice extends BaseTimeEntity implements BaseEntity<PtPriceDto> {

    @Id @GeneratedValue
    @Column(name = "PT_PRICE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  //FK를 가지는 쪽이 Many, FetchType.Lazy로 설정하면 프록시 객체로 조회,GymPrice 엔티티만 DB에서 조회,
    @JoinColumn(name = "MEMBER_ID")     //Member PK
    private Member trainer;    //트레이너

    @Column(name = "REGULAR_PRICE")
    private Long regularPrice;  //정상가

    @Column(name = "DISCOUNT_PRICE")
    private Long discountPrice; //할인가

    @Column(name = "TIMES")
    private Integer times;  //횟수 (ex. 10회, 20회, 30회 등..)


    /**
     *  setter 대신 도메인 객체 변경하는 메서드들(setter 사용 지양)
     */

    public void changeReviewer(Member trainer){
        this.trainer =trainer;
    }

    public void changeRegularPrice(Long regularPrice){
        this.regularPrice=regularPrice;
    }

    public void changeDiscountPrice(Long discountPrice){
        this.discountPrice=discountPrice;
    }

    public void changeTimes(Integer times){
        this.times=times;
    }

    /* ============================================================================================================== */

    @Builder    //객체 생성(빌더 패턴)
    public PtPrice(Member trainer, Long regularPrice, Long discountPrice, Integer times) {
        this.trainer = trainer;
        this.regularPrice = regularPrice;
        this.discountPrice = discountPrice;
        this.times = times;
    }

    public static PtPrice toEntity(PtPriceDto ptPriceDto) {
        return PtPrice.builder()
                .trainer(ptPriceDto.getTrainer())
                .regularPrice(ptPriceDto.getRegularPrice())
                .discountPrice(ptPriceDto.getDiscountPrice())
                .times(ptPriceDto.getTimes())
                .build();
    }

    @Override
    public void updateEntity(PtPriceDto ptPriceDto) {
        changeReviewer(ptPriceDto.getTrainer());
        changeDiscountPrice(ptPriceDto.getDiscountPrice());
        changeRegularPrice(ptPriceDto.getRegularPrice());
        changeTimes(ptPriceDto.getTimes());
    }
}
