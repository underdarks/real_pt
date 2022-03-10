package health.real_pt.member.service;

import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberDto;
import health.real_pt.member.repository.MemberRepository;
import health.real_pt.member.repository.MySqlMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.Optional;


@SpringBootTest
class MeberServiceImplTest {

    @Autowired EntityManager em;

    MemberRepository memberRepository;
    MemberService memberService;


    @BeforeEach
    public void beforeEach(){
        memberRepository=new MySqlMemberRepository(em);
        memberService=new MeberServiceImpl(memberRepository);
    }


    @Test
    public void 회원가입_테스트(){
        //given
//        MemberDto member = new MemberDto();
//        member.set
//
//        //when
//        memberService.join(member);
//
//        Optional<Member> findMember = memberRepository.findById(1L);
//
//        //then
//        Assertions.assertThat(findMember.get().getId()).isEqualTo(member.getId());
    }

}