package health.real_pt.gym.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Clob;

@Getter @Setter
public class UpdateGymDto {

    private Long id;

    private String name;    //헬스장 이름

    private Clob Info;      //헬스장 정보

    private Clob openTime;    //영업 시간

    private Clob program;      //운영 프로그램

    private String location;    //위치 주소

    private Clob extraService;  //부가서비스

    private Clob Facilites;     //편의시설
}
