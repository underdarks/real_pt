package health.real_pt.price.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.service.GymService;
import health.real_pt.price.dto.gymPrice.GymPriceResDto;
import health.real_pt.price.dto.gymPrice.GymPriceReqDto;
import health.real_pt.price.service.gymPrice.GymPriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


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
        Gym gym = gymService.findOne(10L);

        //2. Dto 생성
        GymPriceReqDto gymPriceReqDto =new GymPriceReqDto();
        gymPriceReqDto.setGym(gym);
        gymPriceReqDto.setId(1L);
        gymPriceReqDto.setRegularPrice(100000L);
        gymPriceReqDto.setDiscountPrice(90000L);
        gymPriceReqDto.setMonths(1);


        //when
        Long saveId = gymPriceService.savePrice(gymPriceReqDto, gym.getId());

        //then
        assertThat(saveId).isGreaterThan(0);
    }


    @Test
    public void 가격등록_실패(){
        //given
        //1. DTO 생성
        GymPriceReqDto gymPriceReqDto =new GymPriceReqDto();
        gymPriceReqDto.setRegularPrice(100000L);
        gymPriceReqDto.setDiscountPrice(90000L);
        gymPriceReqDto.setMonths(1);

        //when
        Long saveId = gymPriceService.savePrice(gymPriceReqDto, 100L);

        //then
        assertThat(saveId).isGreaterThan(0);
    }

    @Test
    public void 가격수정_성공(){
        //given
        //1. DTO 생성
        Long gymId=10L;
        Gym gym = gymService.findOne(gymId);

        GymPriceReqDto gymPriceReqDto =new GymPriceReqDto();
        gymPriceReqDto.setId(15L);
        gymPriceReqDto.setGym(gym);
        gymPriceReqDto.setRegularPrice(250000L);
        gymPriceReqDto.setDiscountPrice(240000L);
        gymPriceReqDto.setMonths(1);


        //when
        GymPriceResDto gymPriceResDto = gymPriceService.updatePrice(gymPriceReqDto);

        //then
        assertThat(gymPriceResDto.getId()).isEqualTo(gymPriceReqDto.getId());    //isEqualTo는 값 자체의 비교
    }

    @Test
    public void 가격삭제_성공(){
        //given
        GymPriceReqDto gymPriceReqDto =new GymPriceReqDto();
        gymPriceReqDto.setId(15L);
        gymPriceReqDto.setRegularPrice(250000L);
        gymPriceReqDto.setDiscountPrice(240000L);
        gymPriceReqDto.setMonths(1);


        //when
        gymPriceService.deletePrice(gymPriceReqDto.getId());


        //then
    }

    @Test
    public void 가격조회_성공(){
        //given
//        Long gymId=10L;
//
//        //when
//        List<GymPrice> gymPriceList = gymPriceService.findAllPrice(gymId);
//
//        for (GymPrice gymPrice : gymPriceList) {
//            System.out.println("gymPrice = " + gymPrice);
//        }
//
//        //then
//        assertThat(gymPriceList).isNotEmpty();
    }

    @Test
    public void 가격조회_실패(){
        //given
        Long gymId=0L;

        //when
//        GymPriceListDto gymPriceList = gymPriceService.findAllPrice(gymId);
//
//        for (GymPrice gymPrice : gymPriceList) {
//            System.out.println("gymPrice = " + gymPrice);
//        }

        //then
    }



}