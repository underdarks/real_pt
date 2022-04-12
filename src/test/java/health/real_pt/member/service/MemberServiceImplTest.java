package health.real_pt.member.service;

import health.real_pt.member.repository.MemberRepository;
import health.real_pt.member.repository.MySqlMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;


@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;


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


    @Test
    public void 회원탈퇴_테스트(){
        //given
        memberService.quit(4L);

        //when


        //then
    }
}