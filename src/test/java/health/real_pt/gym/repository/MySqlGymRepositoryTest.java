package health.real_pt.gym.repository;

import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.ExceptionInterceptorChain;
import com.mysql.cj.jdbc.Clob;
import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.domain.GymStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.EntityManager;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MySqlGymRepositoryTest {

    @Autowired
    GymRepository gymRepository;

    @Test
    @Commit
    public void Gym_객체생성(){
        //given
        Gym gym = Gym.builder()
                .name("C 헬스장")
                .info("우리 헬스장은 샬라샬라~~~~~~~~~~~~~~~~~~~~~~~~ ")
                .openTime("06:00 ~ 24:00")
                .program("")
                .location("XX시 XX구 ....")
                .facilites("뉴텍 최신기구..... ")
                .extraService("wifi 물 옷 수건 호갱pt 등..")
                .gymStatus(GymStatus.OPEN)
                .build();

        //when
        Long saveId = gymRepository.save(gym);

        //then
        System.out.println("saveId = " + saveId);

    }

    @Test
    public void Gym_객체찾기_성공(){
        //given
        Optional<Gym> gymOptional = gymRepository.findById(6L);


        //when
        Gym gym = gymOptional.orElseThrow(() -> new NoSuchElementException("Gym 객체가 없습니다!"));

        assertThat(gym).isNotNull();


//        gymOptional.ifPresent(
//                //then
//                gym -> assertThat(gym).isNotNull()
//        );

    }

    @Test
    public void Gym_객체찾기_실패(){
        //given
        Optional<Gym> gymOptional = gymRepository.findById(3L);

        //when
        Gym gym = gymOptional.orElse(null);

        //then
        assertThat(gym).isNull();
    }


    @Test
    @Commit
    public void Gym_객체수정_성공(){
        Optional<Gym> gymOptional = gymRepository.findById(6L);

        System.out.println("before gymOptional.toString() = " + gymOptional.toString());


        //Dirty-Check
        Gym gym = gymOptional.orElseThrow(() -> new NoSuchElementException("Gym 객체가 없습니다!"));
        gym.changeName("헬스장 이름 바꿈");

        System.out.println("after gymOptional.toString() = " + gymOptional.toString());
    }

    @Test
    @Commit
    public void Gym_객체삭제_성공(){
        //given
        Optional<Gym> gymOptional = gymRepository.findById(7L);
        System.out.println("gymOptional = " + gymOptional);

        //when
        Gym gym = gymOptional.orElseThrow(() -> new NoSuchElementException("Gym 객체가 없습니다!"));
        gymRepository.delete(gym);

        //then
        Optional<Gym> gymOptional2 = gymRepository.findById(5L);
        System.out.println("gymOptional2 = " + gymOptional2);

    }



}