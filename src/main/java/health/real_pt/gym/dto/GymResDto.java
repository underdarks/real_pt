package health.real_pt.gym.dto;

import health.real_pt.common.BaseDto;
import health.real_pt.gym.domain.Gym;
import lombok.Data;

@Data
public class GymResDto implements BaseDto<Gym, GymResDto> {

    private Long id;

    private String name;    //헬스장 이름

    private String info;      //헬스장 정보

    private String openTime;    //영업 시간

    private String program;      //운영 프로그램

    private String location;    //위치 주소

    private String extraService;  //부가서비스

    private String Facilites;     //편의시설

    @Override
    public GymResDto entityToDto(Gym gym) {
        GymResDto gymReqDto = new GymResDto();

        gymReqDto.setId(gym.getId());
        gymReqDto.setName(gym.getName());
        gymReqDto.setInfo(gym.getInfo());
        gymReqDto.setOpenTime(gym.getOpenTime());
        gymReqDto.setProgram(gym.getProgram());
        gymReqDto.setLocation(gym.getLocation());
        gymReqDto.setExtraService(gym.getExtraService());
        gymReqDto.setFacilites(gym.getFacilites());

        return gymReqDto;
    }
}
