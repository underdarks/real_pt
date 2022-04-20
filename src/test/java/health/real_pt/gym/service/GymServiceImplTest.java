package health.real_pt.gym.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymReqDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class GymServiceImplTest {

    @Autowired
    GymService gymService;

    public GymReqDto makeGymDto(){
        GymReqDto gymReqDto =new GymReqDto();

        gymReqDto.setId(9L);
        gymReqDto.setName("석스장 본점");
        gymReqDto.setExtraService("와이파이 무료 ...");
        gymReqDto.setInfo("하고싶은거다해~");
        gymReqDto.setLocation("서울시 ......");
        gymReqDto.setOpenTime("00:00 ~ 24:00 24/7");

        return gymReqDto;
    }

    @DisplayName("저장 로직 테스트")
    @Test
    public void Gym_저장(){
        //given
        GymReqDto gymReqDto =new GymReqDto();
        gymReqDto.setName("스포애니 신림사거리 1호점");
        gymReqDto.setExtraService("와이파이 무료 ...");
        gymReqDto.setInfo("헬창 많음");
        gymReqDto.setLocation("서울시 ......");
        gymReqDto.setOpenTime("00:00 ~ 24:00 24/7");

        //when
        Long saveId = gymService.saveGym(gymReqDto,null);


        //then
        System.out.println("saveId = " + saveId);
    }



}