package health.real_pt.price.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.domain.Gym;
import health.real_pt.price.dto.gymPrice.GymPriceReqDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "GYM_PRICE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class GymPrice extends BaseTimeEntity implements BaseEntity<GymPriceReqDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GYM_PRICE_ID")
    private Long id;

    @NotNull
    @ManyToOne(fetch = LAZY)  //FK를 가지는 쪽이 Many, FetchType.Lazy로 설정하면 프록시 객체로 조회,GymPrice 엔티티만 DB에서 조회,
    @JoinColumn(name = "GYM_ID")
    private Gym gym;    //GYM PK

    @Column(name = "REGULAR_PRICE")
    private Long regularPrice;  //정상가

    @Column(name = "DISCOUNT_PRICE")
    private Long discountPrice; //할인가

    @Column(name = "MONTHS")
    private String months;  //월수 (ex. 1개월, 3개월, 6개월 혹은 일일권)


    /**
     * setter 대신 도메인 객체 변경하는 메서드들(setter 사용 지양)
     */

    public void changeGym(Gym gym) {
        this.gym = gym;
    }

    public void changeRegularPrice(Long regularPrice) {
        this.regularPrice = regularPrice;
    }

    public void changeDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void changeMonth(String months) {
        this.months = months;
    }

    /* ============================================================================================================== */


    //====== 연관관계 편의메서드
    public void addGymPrice(Gym gym) {
        this.gym=gym;
        this.gym.getPrices().add(this);
    }


    //객체 생성(빌더 패턴)
    @Builder
    public GymPrice(Gym gym, Long regularPrice, Long discountPrice, String months) {
        this.regularPrice = regularPrice;
        this.discountPrice = discountPrice;
        this.months = months;

        addGymPrice(gym);
    }

    //DTO -> Entity로 변환
    public static GymPrice toEntity(GymPriceReqDto gymPriceReqDto) {
        return GymPrice.builder()
                .gym(gymPriceReqDto.getGym())
                .regularPrice(gymPriceReqDto.getRegularPrice())
                .discountPrice(gymPriceReqDto.getDiscountPrice())
                .months(gymPriceReqDto.getMonths())
                .build();
    }

    @Override
    public void updateEntity(GymPriceReqDto gymPriceReqDto) {
        if (gymPriceReqDto.getGym() != null)
            changeGym(gymPriceReqDto.getGym());

        if (gymPriceReqDto.getRegularPrice() != null)
            changeRegularPrice(gymPriceReqDto.getRegularPrice());

        if (gymPriceReqDto.getDiscountPrice() != null)
            changeDiscountPrice(gymPriceReqDto.getDiscountPrice());

        if (gymPriceReqDto.getMonths() != null)
            changeMonth(gymPriceReqDto.getMonths());
    }
}
