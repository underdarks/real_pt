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
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Transactional  //테스트 코드에 @Transactional이 있으면 자동으로 RollBack 해버리는데 @Commit이 있으면 롤백이 안됨
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
        //given
        Member member = Member.builder()
                .userId("1234")
                .name("test")
                .password("12314")
                .email("test12")
                .birthDay(LocalDate.now())
                .build();




        //when
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findById(1L);

        //then
        Assertions.assertThat(findMember.get().getId()).isEqualTo(member.getId());
    }

    @Test
    public void 회원수정_테스트(){
        //given
        Member findMember = memberRepository.findById(1L).get();


        //when


        //then
    }

    public Member getMember(){
        Member member = Member.builder()
                .name("test")
                .email("test12")
                .build();


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