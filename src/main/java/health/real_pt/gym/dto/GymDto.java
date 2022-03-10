package health.real_pt.gym.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GymDto {

    private Long id;
    private String name;    //헬스장 이름
    private String Info;      //헬스장 정보
    private String openTime;    //영업 시간
    private String program;      //운영 프로그램
    private String location;    //위치 주소
    private String extraService;  //부가서비스
    private String Facilites;     //편의시설

}
