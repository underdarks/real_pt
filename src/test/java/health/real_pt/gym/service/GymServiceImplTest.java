package health.real_pt.gym.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.dto.GymDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class GymServiceImplTest {

    @Autowired
    GymService gymService;

    public GymDto makeGymDto(){
        GymDto gymDto=new GymDto();

        gymDto.setId(9L);
        gymDto.setName("석스장 본점");
        gymDto.setExtraService("와이파이 무료 ...");
        gymDto.setInfo("하고싶은거다해~");
        gymDto.setLocation("서울시 ......");
        gymDto.setOpenTime("00:00 ~ 24:00 24/7");

        return gymDto;
    }

    @DisplayName("저장 로직 테스트")
    @Test
    public void Gym_저장(){
        //given
        GymDto gymDto=new GymDto();
        gymDto.setName("스포애니 신림사거리 1호점");
        gymDto.setExtraService("와이파이 무료 ...");
        gymDto.setInfo("헬창 많음");
        gymDto.setLocation("서울시 ......");
        gymDto.setOpenTime("00:00 ~ 24:00 24/7");

        //when
        Long saveId = gymService.saveGym(gymDto);


        //then
        System.out.println("saveId = " + saveId);
    }

    @Test
    public void Gym_단일조회_성공(){
        //given
        Optional<Gym> optional = gymService.findOne(9L);
        Gym gym = optional.orElseThrow(() -> new NoSuchElementException("Gym 객체를 조회하지 못했습니당!"));

        //when

        System.out.println("gym = " + gym);

        //then

        assertThat(optional).isNotEmpty();
    }

    @Test
    public void Gym_단일조회_실패(){
        //given
        Optional<Gym> optional = gymService.findOne(12341L);

        //when

        //then
        assertThat(optional).isEmpty();
    }

    @Test
    public void Gym_전체조회(){
        //given
        List<Gym> gyms = gymService.findGyms();


        //when
        for (int i = 0; i < gyms.size(); i++) {
            System.out.println((i+1) + "번째 -> " + " gym = " + gyms.get(i));
        }


        //then
    }

    @Test
    public void Gym_업데이트(){
        //given
        GymDto gymDto = makeGymDto();

        //when
        gymService.updateGym(gymDto);

        //then
    }


}