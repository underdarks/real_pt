## 헬스장 관리
헬스장 정보(헬스장 이름, 영업 시간, 운영 프로그램, 위치 주소, 부가 서비스, 편의 시설 등)를 관리합니다.
<br>
<br>

<h2>헬스장 엔티티 </h2>
아래 코드는 헬스장 엔티티이다. <br>
회원 엔티티(Member)랑 양방향 관계(mappedBy)를 설정하려 했으나 회원 테이블이 연관 관계 주인인 단방향 매핑을 하였.<br><br>

~~~java
package health.real_pt.gym.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.dto.GymReqDtoDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity @Table(name = "GYM") @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class Gym extends BaseTimeEntity implements BaseEntity<GymReqDto> {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GYM_ID")
    private Long id;

    @NotBlank
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
    public static Gym toEntity(GymReqDto gymReqDto){
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
    public void updateEntity(GymReqDto gymReqDto) {
        changeName(gymReqDto.getName());
        changeInfo(gymReqDto.getInfo());
        changeOpenTime(gymReqDto.getOpenTime());
        changeProgram(gymReqDto.getProgram());
        changeLocation(gymReqDto.getLocation());
        changeExtraService(gymReqDto.getExtraService());
        changeFacilites(gymReqDto.getFacilites());
    }

}

~~~


<br>
<h2>헬스장 API Controller</h2>

~~~java
package health.real_pt.gym.api;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymReqDtoDto;
import health.real_pt.gym.dto.GymResDto;
import health.real_pt.gym.service.GymService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/gym")
@RequiredArgsConstructor
public class GymApiController {

    private final GymService gymService;

    /**
     * 헬스장 정보 저장
     * @param reqGymReqDto : 저장할 헬스장 정보 DTO
     * @return : ID(PK)값
     */
    @ApiOperation(value = "헬스장 등록", notes = "헬스장 정보를 등록합니다.")
    @PostMapping("")
    public Long saveGym(@RequestBody @Valid GymReqDto reqGymReqDto){
        return gymService.saveGym(reqGymReqDto);
    }


    /**
     * 헬스장 정보 수정
     * @param id        : 수정할 ID(PK)
     * @param updGymReqDto : 수정할 헬스장 정보 DTO
     * @return          : 수정된 DTO
     */
    @ApiOperation(value = "헬스장 수정", notes = "id를 받아 헬스장 정보를 수정합니다.")
    @PatchMapping("/{id}")
    public GymReqDto updateGym(@PathVariable("id") Long id, @RequestBody @Valid GymReqDto updGymReqDto){
        gymService.updateGym(id,updGymReqDto);
        Gym gym = gymService.findOne(id);

        return new GymReqDto().entityToDto(gym);
    }

    /**
     * 단일 헬스장 조회
     * @param id : 헬스장 ID(PK)
     * @return   : GymDTO
     */
    @ApiOperation(value = "단일 헬스장 조회", notes = "id를 받아 헬스장 정보를 조회합니다.")
    @GetMapping("/{id}")
    public GymReqDto findGym(@PathVariable("id") Long id){
        Gym gym = gymService.findOne(id);

        return new GymReqDto().entityToDto(gym);
    }

    /**
     * 모든 헬스장 조회
     * @return :GymResultDTO
     */
    @ApiOperation(value = "전체 헬스장 조회", notes = "모든 헬스장 정보를 조회합니다.")
    @GetMapping("")
    public GymResDto findAllGym(){
        List<Gym> findGyms = gymService.findGyms();

        //Entity List -> Dto List
        List<GymReqDto> gymReqDtoList = findGyms.stream()
                .map(gym -> new GymReqDto().entityToDto(gym))
                .collect(Collectors.toList());


        return new GymResDto(gymReqDtoList.size(),gymReqDtoList);
    }


    /**
     *
     * 헬스장 삭제
     * @return 성공 여부
     */
    @ApiOperation(value = "헬스장 삭제", notes = "id를 받아 헬스장 정보를 삭제합니다.")
    @DeleteMapping("/{id}")
    public String deleteGym(@PathVariable("id") Long id){
        gymService.deleteGym(id);

        return "success";
    }


}

~~~

<br>
<h2>API 기능</h2>

- #### 기능 
- #### 기능 


<br>
<h2>API 상세 설명 및 테스트 </h2>
 
<h3>xx 기능</h3>

<br>
<h2>API 문서 관리</h2>
swaager를 활용하여 API 문서 관리 자동화<br><br>




