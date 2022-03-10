package health.real_pt.gym.repository;

import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.ExceptionInterceptorChain;
import com.mysql.cj.jdbc.Clob;
import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.domain.GymStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@Transactional
class MySqlGymRepositoryTest {

    @Autowired
    GymRepository gymRepository;

    @Test
    public void Gym_객채생성_테스트(){
        //given
        Gym gym = Gym.builder()
                .name("B 헬스장")
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

}