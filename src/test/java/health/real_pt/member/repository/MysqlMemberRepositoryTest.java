package health.real_pt.member.repository;

import health.real_pt.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Transactional
class MysqlMemberRepositoryTest {

    @Autowired EntityManager em;
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository=new MysqlMemberRepository(em);
    }



    @DisplayName("DB Insert 테스트")
    @Test
    @Commit
    public void 회원저장_테스트() {

//        if(em == null)
//            System.out.println("em 이 널이다!!");
//
//        if(memberRepository == null)
//            System.out.println("memberRepository 이 널이다!!");

        //given
        Member member = new Member();
        member.setUserId("id1");
        member.setName("석재현");
        member.setNickname("헬창퀀트");
        member.setEmail("rkdrl45617@gmail.com");

        //when
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findById(1L);

        //then
        Assertions.assertThat(findMember.get().getId()).isEqualTo(member.getId());
    }

    public Member getMember(){
        Member member = new Member();
        member.setUserId("id1");
        member.setName("test");
        member.setNickname("testName");
        member.setEmail("test@gmail.com");
//        member.setBirthDay(LocalDate.now());

        return member;
    }


    @DisplayName("@NotNull 어노테이션 테스트")
    @Test
    @Commit
    public void NotNull_테스트(){
        //given
        Member member=getMember();

        em.persist(member);

        //when


        //then
    }

    @DisplayName("@nullable 어노테이션 테스트")
    @Test
    @Commit
    public void nullable_테스트(){
        //given
        Member member=getMember();

        em.persist(member);

        //when


        //then
    }



}