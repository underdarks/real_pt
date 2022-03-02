package health.real_pt.gym.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.dto.GymDto;
import lombok.*;

import javax.persistence.*;
import java.sql.Clob;

@Entity @Table(name = "GYM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class Gym extends BaseTimeEntity implements BaseEntity<Gym,GymDto> {

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

    /**
     *  setter 대신 도메인 객체 변경하는 메서드들
     */

    public void changeName(String name){
        this.name=name;
    }

    public void changeInfo(Clob info){
        this.info=info;
    }

    public void changeOpenTime(Clob openTime){
        this.openTime=openTime;
    }

    public void changeProgram(Clob program){
        this.program=program;
    }

    public void changeLocation(String location){
        this.location=location;
    }

    public void changeExtraService(Clob extraService){
        this.extraService=extraService;
    }

    public void changeFacilites(Clob facilites){
        this.facilites=facilites;
    }


    /* ============================================================================================================== */

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

    //Dto -> Entity로 변환(객체 생성)
    public static Gym toEntity(GymDto gymDto){
        return Gym.builder()
                .name(gymDto.getName())
                .info(gymDto.getInfo())
                .openTime(gymDto.getOpenTime())
                .program(gymDto.getProgram())
                .location(gymDto.getLocation())
                .extraService(gymDto.getExtraService())
                .facilites(gymDto.getFacilites())
                .build();

    }

    //Entity 수정을 위한 공통 메서드
    @Override
    public void updateEntity(GymDto gymDto) {
        changeName(gymDto.getName());
        changeInfo(gymDto.getInfo());
        changeOpenTime(gymDto.getOpenTime());
        changeProgram(gymDto.getProgram());
        changeLocation(gymDto.getLocation());
        changeExtraService(gymDto.getExtraService());
        changeFacilites(gymDto.getFacilites());
    }

}