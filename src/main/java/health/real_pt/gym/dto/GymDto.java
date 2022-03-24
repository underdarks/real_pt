package health.real_pt.gym.dto;

import health.real_pt.common.BaseDto;
import health.real_pt.gym.domain.Gym;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GymDto implements BaseDto<Gym,GymDto> {

    private Long id;

    @NotBlank
    private String name;    //헬스장 이름

    private String info;      //헬스장 정보

    private String openTime;    //영업 시간

    private String program;      //운영 프로그램

    @NotBlank
    private String location;    //위치 주소

    private String extraService;  //부가서비스

    private String Facilites;     //편의시설

    @Override
    public GymDto entityToDto(Gym gym) {
        GymDto gymDto = new GymDto();

        gymDto.setId(gym.getId());
        gymDto.setName(gym.getName());
        gymDto.setInfo(gym.getInfo());
        gymDto.setOpenTime(gym.getOpenTime());
        gymDto.setProgram(gym.getProgram());
        gymDto.setLocation(gym.getLocation());
        gymDto.setExtraService(gym.getExtraService());
        gymDto.setFacilites(gym.getFacilites());

        return gymDto;
    }
}
