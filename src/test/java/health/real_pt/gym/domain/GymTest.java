package health.real_pt.gym.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class GymTest {

    @Test
    public void Gym_Protected_객체생성_테스트(){
        //given
        Gym gym=Gym.builder()
                .name("test")
                .build();


        //when

        System.out.println("gym = " + gym);




        //then
    }

}