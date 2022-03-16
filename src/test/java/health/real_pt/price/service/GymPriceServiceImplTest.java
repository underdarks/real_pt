package health.real_pt.price.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.repository.GymRepository;
import health.real_pt.gym.service.GymService;
import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.dto.GymPriceDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class GymPriceServiceImplTest {

    @Autowired
    GymPriceService gymPriceService;

    @Autowired
    GymService gymService;


    @Test
    public void 가격등록_성공(){
        //given
        //1. Gym 찾기
        Optional<Gym> gymOptional = gymService.findOne(10L);
        Gym gym = gymOptional.orElseThrow(() -> new NoSuchElementException("Gym 객체가 없습니다!"));

        //2. Dto 생성
        GymPriceDto gymPriceDto=new GymPriceDto();
        gymPriceDto.setGym(gym);
        gymPriceDto.setId(1L);
        gymPriceDto.setRegularPrice(100000L);
        gymPriceDto.setDiscountPrice(90000L);
        gymPriceDto.setMonths(1);


        //when
        Long saveId = gymPriceService.saveGymPrice(gymPriceDto, 1L, gym.getId());

        //then
        assertThat(saveId).isGreaterThan(0);
    }


    @Test
    public void 가격등록_실패(){
        //given
        //1. DTO 생성
        GymPriceDto gymPriceDto=new GymPriceDto();
        gymPriceDto.setRegularPrice(100000L);
        gymPriceDto.setDiscountPrice(90000L);
        gymPriceDto.setMonths(1);

        //when
        Long saveId = gymPriceService.saveGymPrice(gymPriceDto, 1L, 100L);

        //then
        assertThat(saveId).isGreaterThan(0);
    }

    @Test
    public void 가격수정_성공(){
        //given
        //1. DTO 생성
        Long gymId=10L;
        Optional<Gym> gymOptional = gymService.findOne(gymId);
        Gym gym = gymOptional.orElseThrow();


        GymPriceDto gymPriceDto=new GymPriceDto();
        gymPriceDto.setId(15L);
        gymPriceDto.setGym(gym);
        gymPriceDto.setRegularPrice(250000L);
        gymPriceDto.setDiscountPrice(240000L);
        gymPriceDto.setMonths(1);


        //when
        Long updateId = gymPriceService.updateGymPrice(gymPriceDto);

        //then
        assertThat(updateId).isEqualTo(gymPriceDto.getId());    //isEqualTo는 값 자체의 비교
    }

    @Test
    public void 가격삭제_성공(){
        //given
        GymPriceDto gymPriceDto=new GymPriceDto();
        gymPriceDto.setId(15L);
        gymPriceDto.setRegularPrice(250000L);
        gymPriceDto.setDiscountPrice(240000L);
        gymPriceDto.setMonths(1);


        //when
        gymPriceService.deleteGymPrice(gymPriceDto);


        //then
    }

    @Test
    public void 가격조회_성공(){
        //given
        Long gymId=10L;

        //when
        List<GymPrice> gymPriceList = gymPriceService.findAllPrice(gymId);

        for (GymPrice gymPrice : gymPriceList) {
            System.out.println("gymPrice = " + gymPrice);
        }

        //then
        assertThat(gymPriceList).isNotEmpty();
    }

    @Test
    public void 가격조회_실패(){
        //given
        Long gymId=0L;

        //when
        List<GymPrice> gymPriceList = gymPriceService.findAllPrice(gymId);

        for (GymPrice gymPrice : gymPriceList) {
            System.out.println("gymPrice = " + gymPrice);
        }

        //then
        assertThat(gymPriceList).isNullOrEmpty();
    }



}