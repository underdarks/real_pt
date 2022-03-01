package health.real_pt.config;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.repository.GymRepository;
import health.real_pt.gym.repository.MysqlGymRepository;
import health.real_pt.gym.service.GymService;
import health.real_pt.gym.service.GymServiceImpl;
import health.real_pt.member.repository.MemberRepository;
import health.real_pt.member.repository.MysqlMemberRepository;
import health.real_pt.member.service.MeberServiceImpl;
import health.real_pt.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class BeanConfig {

    EntityManager em;

    @Autowired
    public BeanConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("BeanConfig - MysqlMemberRepository 빈 의존성 주입");
        return new MysqlMemberRepository(em);
    }


    @Bean
    public MemberService memberService(){
        System.out.println("BeanConfig - MemberService 빈 의존성 주입");
        return new MeberServiceImpl(memberRepository());
    }

    @Bean
    public GymRepository gymRepository(){
        return new MysqlGymRepository(em);
    }

    @Bean
    public GymService gymService(){
        return new GymServiceImpl(gymRepository());
    }


}
