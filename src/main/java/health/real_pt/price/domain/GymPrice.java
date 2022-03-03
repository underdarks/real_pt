package health.real_pt.price.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.domain.Gym;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity @Table(name = "GYM_PRICE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class GymPrice extends BaseTimeEntity implements BaseEntity {

    @Id @GeneratedValue
    @Column(name = "GYM_PRICE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  //FK를 가지는 쪽이 Many, FetchType.Lazy로 설정하면 프록시 객체로 조회,GymPrice 엔티티만 DB에서 조회,
    @JoinColumn(name = "GYM_ID")
    private Gym gym;    //GYM PK

    @Column(name = "REGULAR_PRICE")
    private Long regularPrice;  //정상가

    @Column(name = "DISCOUNT_PRICE")
    private Long discountPrice; //할인가

    @Column(name = "MONTHS")
    private Integer months;  //월수 (ex. 1개월, 3개월, 6개월 등)



    @Override
    public void updateEntity(Object o) {

    }
}
