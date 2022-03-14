package health.real_pt.price.repository;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.repository.GymRepository;
import health.real_pt.price.domain.GymPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MySqlGymPriceRepositoryTest {

    @Autowired
    GymPriceRepository gymPriceRepository;

    @Autowired
    GymRepository gymRepository;


    @Test
    public void Gym가격_저장(){
        //1.헬스장 찾기
        Optional<Gym> gymOptional = gymRepository.findById(9L);

        gymOptional.orEls


        //given
        GymPrice.builder().


                build();

        //when


        //then
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }
}