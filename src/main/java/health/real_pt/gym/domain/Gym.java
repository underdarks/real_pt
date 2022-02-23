package health.real_pt.gym.domain;

import health.real_pt.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "GYM")
public class Gym extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "GYM_ID")
    private Long id;

    private String name;    //헬스장 이름

    private String openTime;    //영업 시간

    private String location;    //위치 주소


}