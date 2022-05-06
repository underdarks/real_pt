package health.real_pt.price.repository;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.repository.GymRepository;
import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.repository.gymPrice.GymPriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MySqlGymPriceRepositoryTest {

    @Autowired
    GymPriceRepository gymPriceRepository;

    @Autowired
    GymRepository gymRepository;


    @Test
    @Commit
    public void GymPrice_저장_성공() {
        //given

        //1. 헬스장 찾기
        Optional<Gym> gymOptional = gymRepository.findById(9L);
        Gym gym = gymOptional.orElseThrow(() -> new NoSuchElementException("Gym 객체 조회 실패!"));

        //2. GymPrice 객체 생성
        GymPrice gymPrice = GymPrice.builder()
                .gym(gym)
                .discountPrice(690000L)
                .regularPrice(580000L)
                .months("12")
                .build();

        //when
        Long saveId = gymPriceRepository.save(gymPrice);

        //then
        assertThat(saveId).isGreaterThan(0L);
    }

    @Test
    @Commit
    public void GymPrice_저장_실패() {
        //given

        //1. 헬스장 찾기
        Optional<Gym> gymOptional = gymRepository.findById(1L);
        Gym gym = gymOptional.orElseThrow(() -> new NoSuchElementException("Gym 객체 조회 실패!"));

        //2. GymPrice 객체 생성
        GymPrice gymPrice = GymPrice.builder()
                .gym(gym)
                .discountPrice(120000L)
                .regularPrice(180000L)
                .months("3")
                .build();

        //when
        Long saveId = gymPriceRepository.save(gymPrice);

        //then
        assertThat(saveId).isGreaterThan(0L);
    }

    @Test
    public void GymPrice_단일가격조회_성공() {
        //given
        Optional<GymPrice> gpOptional = gymPriceRepository.findById(12L);

        //when
        GymPrice gymPrice = gpOptional.orElseThrow((() -> new NoSuchElementException("GymPrice 객체를 찾을 수 없습니다")));

        //then
        assertThat(gymPrice).isNotNull();
    }

    @Test
    public void GymPrice_단일가격조회_실패() {
        //given
        Optional<GymPrice> gpOptional = gymPriceRepository.findById(1L);

        //when
        GymPrice gymPrice = gpOptional.orElse(null);

        //then
        assertThat(gymPrice).isNull();
    }

    @Test
    public void GymPrice_전체_조회_성공() {
        //given
        List<GymPrice> gymPriceList = gymPriceRepository.findByGymId(9L);

        //when
        if (!gymPriceList.isEmpty()) {
            for (int i = 0; i < gymPriceList.size(); i++) {
                System.out.println((i + 1) + "번째 -> " + " gymPrice = " + gymPriceList.get(i));
            }
        }

        //then
    }

}