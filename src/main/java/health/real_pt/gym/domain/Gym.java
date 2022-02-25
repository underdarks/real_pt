package health.real_pt.gym.domain;

import health.real_pt.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Clob;

@Entity
@Getter
@Table(name = "GYM")
public class Gym extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "GYM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;    //헬스장 이름

    @Column(name = "INFO")
    private Clob Info;      //헬스장 정보

    @Column(name = "OPEN_TIME")
    private Clob openTime;    //영업 시간

    @Column(name = "PROGRAM")
    private Clob program;      //운영 프로그램

    @Column(name = "LOCATION")
    private String location;    //위치 주소

    @Column(name = "EXTRA_SERVICE")
    private Clob extraService;  //부가서비스

    @Column(name = "FACILITIES")
    private Clob Facilites;     //편의시설

}