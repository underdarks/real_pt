package health.real_pt.member.service;

import health.real_pt.config.BeanConfig;
import health.real_pt.member.domain.Member;
import health.real_pt.member.repository.MemberRepository;
import health.real_pt.member.repository.MysqlMemberRepository;
import org.assertj.core.api.Assertions;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MeberServiceImplTest {

    @Autowired EntityManager em;

    MemberRepository memberRepository;
    MemberService memberService;


    @BeforeEach
    public void beforeEach(){
        memberRepository=new MysqlMemberRepository(em);
        memberService=new MeberServiceImpl(memberRepository);
    }


    @Test
    public void 회원가입_테스트(){
        //given
        Member member=new Member();
        member.setUserId("id10");
        member.setName("김회원");
        member.setNickname("닉네임1");
        member.setEmail("rkdrl45617@gmail.com");

        //when
        memberService.join(member);

        Optional<Member> findMember = memberRepository.findById(1L);

        //then
        Assertions.assertThat(findMember.get().getId()).isEqualTo(member.getId());
    }

}