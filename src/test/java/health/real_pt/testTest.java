package health.real_pt;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class testTest {

    @Test
    public void LocalDate_LocalDateTime_비교(){
        LocalDate localDate=LocalDate.of(2022,2,15);
        LocalDateTime localDateTime=LocalDateTime.of(2022,02,15,10,55,30);

        System.out.println("localDate = " + localDate);
        System.out.println("localDateTime = " + localDateTime);
    }


}