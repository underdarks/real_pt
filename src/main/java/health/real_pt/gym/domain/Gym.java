package health.real_pt.gym.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.dto.GymDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Table(name = "GYM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class Gym extends BaseTimeEntity implements BaseEntity<Gym,GymDto> {

    @Id @GeneratedValue
    @Column(name = "GYM_ID")
    private Long id;

    @NotNull
    @Column(name = "NAME", unique = true)
    private String name;    //헬스장 이름(중복 불가, 대신 체인점은 끝에 xx점 붙이기 ex. 스포애니 - 신림사거리 1호점)

    @Lob
    @Column(name = "INFO")
    private String info;      //헬스장 정보

    @Lob
    @Column(name = "OPEN_TIME")
    private String openTime;    //영업 시간

    @Lob
    @Column(name = "PROGRAM")
    private String program;      //운영 프로그램

    @NotNull
    @Column(name = "LOCATION")
    private String location;    //위치 주소

    @Lob
    @Column(name = "EXTRA_SERVICE")
    private String extraService;  //부가서비스

    @Lob
    @Column(name = "FACILITIES")
    private String facilites;     //편의시설

    //Enum타입은 꼭 String으로 써라 Ordinal은 2가지 값만 갖는다. 따라서 확장 안됨
    @Enumerated(EnumType.STRING)

    private GymStatus gymStatus;    //헬스장 영업 상태

    /**
     *  setter 대신 도메인 객체 변경하는 메서드들(setter 사용 지양)
     */

    public void changeName(String name){
        this.name=name;
    }

    public void changeInfo(String info){
        this.info=info;
    }

    public void changeOpenTime(String openTime){
        this.openTime=openTime;
    }

    public void changeProgram(String program){
        this.program=program;
    }

    public void changeLocation(String location){
        this.location=location;
    }

    public void changeExtraService(String extraService){
        this.extraService=extraService;
    }

    public void changeFacilites(String facilites){
        this.facilites=facilites;
    }

    /* ============================================================================================================== */

    @Builder
    public Gym(String name, String info, String openTime, String program, String location, String extraService, String facilites,GymStatus gymStatus){
        this.name=name;
        this.info=info;
        this.openTime=openTime;
        this.program=program;
        this.location=location;
        this.extraService=extraService;
        this.facilites=facilites;
        this.gymStatus=gymStatus;
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