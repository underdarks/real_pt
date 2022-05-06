package health.real_pt.price.repository.ptPrice;

import health.real_pt.price.domain.PtPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MySqlPtPriceRepositoryTest {

    @Autowired
    PtPriceRepository ptPriceRepository;


    @Test
    public void 피티가격_전체조회(){
        //given
        Long ptId=3L;
        Long gymId=1L;


        //when
        List<PtPrice> all = ptPriceRepository.findAll(ptId);

        for (PtPrice ptPrice : all) {
            System.out.println("ptPrice = " + ptPrice);
        }


        //then
    }


}