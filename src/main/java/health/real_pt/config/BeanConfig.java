package health.real_pt.config;

import health.real_pt.gym.repository.GymRepository;
import health.real_pt.gym.repository.MySqlGymRepository;
import health.real_pt.gym.service.GymService;
import health.real_pt.gym.service.GymServiceImpl;
import health.real_pt.member.repository.MemberRepository;
import health.real_pt.member.repository.MySqlMemberRepository;
import health.real_pt.member.service.MemberServiceImpl;
import health.real_pt.member.service.MemberService;
import health.real_pt.price.repository.gymPrice.GymPriceRepository;
import health.real_pt.price.repository.gymPrice.MySqlGymPriceRepository;
import health.real_pt.price.repository.ptPrice.MySqlPtPriceRepository;
import health.real_pt.price.repository.ptPrice.PtPriceRepository;
import health.real_pt.price.service.gymPrice.GymPriceService;
import health.real_pt.price.service.gymPrice.GymPriceServiceImpl;
import health.real_pt.price.service.ptPrice.PtPriceService;
import health.real_pt.price.service.ptPrice.PtPriceServiceImpl;
import health.real_pt.review.repository.ptReview.MysqlPtReviewRepository;
import health.real_pt.review.repository.ptReview.PtReviewRepository;
import health.real_pt.review.service.ptReview.PtReviewService;
import health.real_pt.review.service.ptReview.PtReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class BeanConfig {

    //싱글톤 ?
    EntityManager em;

    @Autowired
    public BeanConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberRepository memberRepository(){
//        System.out.println("BeanConfig - MysqlMemberRepository 빈 의존성 주입");
        return new MySqlMemberRepository(em);
    }


    @Bean
    public MemberService memberService(){
//        System.out.println("BeanConfig - MemberService 빈 의존성 주입");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public GymRepository gymRepository(){
        return new MySqlGymRepository(em);
    }

    @Bean
    public GymService gymService(){
        return new GymServiceImpl(gymRepository());
    }

    @Bean
    public GymPriceRepository gymPriceRepository(){
        return new MySqlGymPriceRepository(em);
    }

    @Bean
    public GymPriceService gymPriceService(){
        return new GymPriceServiceImpl(gymPriceRepository(), gymRepository());
    }

    @Bean
    public PtPriceRepository ptPriceRepository(){
        return new MySqlPtPriceRepository(em);

    }

    @Bean
    public PtPriceService ptPriceService(){
        return new PtPriceServiceImpl(memberRepository(),ptPriceRepository());
    }

    @Bean
    public PtReviewRepository ptReviewRepository(){
        return new MysqlPtReviewRepository(em);
    }

    @Bean
    public PtReviewService ptReviewService(){
        return new PtReviewServiceImpl(ptReviewRepository(), memberRepository());
    }


}
