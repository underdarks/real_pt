package health.real_pt.gym.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.CommonBuilder;
import lombok.*;

import javax.persistence.*;
import java.sql.Clob;

@Entity @Table(name = "GYM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class Gym extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "GYM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;    //헬스장 이름

    @Column(name = "INFO")
    private Clob info;      //헬스장 정보

    @Column(name = "OPEN_TIME")
    private Clob openTime;    //영업 시간

    @Column(name = "PROGRAM")
    private Clob program;      //운영 프로그램

    @Column(name = "LOCATION")
    private String location;    //위치 주소

    @Column(name = "EXTRA_SERVICE")
    private Clob extraService;  //부가서비스

    @Column(name = "FACILITIES")
    private Clob facilites;     //편의시설

    @Builder
    public Gym(String name, Clob info, Clob openTime, Clob program, String location, Clob extraService, Clob facilites){
        this.name=name;
        this.info=info;
        this.openTime=openTime;
        this.program=program;
        this.location=location;
        this.extraService=extraService;
        this.facilites=facilites;
    }

}