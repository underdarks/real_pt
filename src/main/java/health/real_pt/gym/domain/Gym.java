package health.real_pt.gym.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.dto.GymReqResDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity @Table(name = "GYM") @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class Gym extends BaseTimeEntity implements BaseEntity<GymReqResDto> {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GYM_ID")
    private Long id;

    @NotBlank(message = "헬스장 이름은 필수 값 입니다.")
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

    @NotBlank
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
        if(name != null && !name.isEmpty())
            this.name=name;
    }

    public void changeInfo(String info){
        if(info != null && !info.isEmpty())
            this.info=info;
    }

    public void changeOpenTime(String openTime){
        if(openTime != null && !openTime.isEmpty())
            this.openTime=openTime;
    }

    public void changeProgram(String program){
        if(program != null && !program.isEmpty())
            this.program=program;
    }

    public void changeLocation(String location){
        if(location != null && !location.isEmpty())
            this.location=location;
    }

    public void changeExtraService(String extraService){
        if(extraService != null && !extraService.isEmpty())
            this.extraService=extraService;
    }

    public void changeFacilites(String facilites){
        if(facilites != null && !facilites.isEmpty())
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
    public static Gym toEntity(GymReqResDto gymReqDto){
        return Gym.builder()
                .name(gymReqDto.getName())
                .info(gymReqDto.getInfo())
                .openTime(gymReqDto.getOpenTime())
                .program(gymReqDto.getProgram())
                .location(gymReqDto.getLocation())
                .extraService(gymReqDto.getExtraService())
                .facilites(gymReqDto.getFacilites())
                .build();

    }

    //Entity 수정을 위한 공통 메서드
    @Override
    public void updateEntity(GymReqResDto gymReqDto) {
        changeName(gymReqDto.getName());
        changeInfo(gymReqDto.getInfo());
        changeOpenTime(gymReqDto.getOpenTime());
        changeProgram(gymReqDto.getProgram());
        changeLocation(gymReqDto.getLocation());
        changeExtraService(gymReqDto.getExtraService());
        changeFacilites(gymReqDto.getFacilites());
    }

}