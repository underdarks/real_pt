package health.real_pt.price.repository;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.repository.GymRepository;
import health.real_pt.price.domain.GymPrice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MySqlGymPriceRepositoryTest {

    @Autowired
    GymPriceRepository gymPriceRepository;

    @Autowired
    GymRepository gymRepository;


    @Test
    @Commit
    public void GymPrice_저장_성공(){
        //given

        //1. 헬스장 찾기
        Optional<Gym> gymOptional = gymRepository.findById(9L);
        Gym gym = gymOptional.orElseThrow(() -> new NoSuchElementException("Gym 객체 조회 실패!"));

        //2. GymPrice 객체 생성
        GymPrice gymPrice = GymPrice.builder()
                .gym(gym)
                .discountPrice(120000L)
                .regularPrice(180000L)
                .months(3)
                .build();

        //when
        Long saveId = gymPriceRepository.save(gymPrice);

        //then
        assertThat(saveId).isGreaterThan(0L);
    }

    @Test
    @Commit
    public void GymPrice_저장_실패(){
        //given

        //1. 헬스장 찾기
        Optional<Gym> gymOptional = gymRepository.findById(1L);
        Gym gym = gymOptional.orElseThrow(() -> new NoSuchElementException("Gym 객체 조회 실패!"));

        //2. GymPrice 객체 생성
        GymPrice gymPrice = GymPrice.builder()
                .gym(gym)
                .discountPrice(120000L)
                .regularPrice(180000L)
                .months(3)
                .build();

        //when
        Long saveId = gymPriceRepository.save(gymPrice);

        //then
        assertThat(saveId).isGreaterThan(0L);
    }

    @Test
    public void Gym(){
        //given


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