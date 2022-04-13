package health.real_pt.member.service;

import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberResDto;
import health.real_pt.member.dto.MemberReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberServiceImpl memberService;


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


    @Test
    public void 회원저장_테스트(){
        //given
        MemberReqDto reqDto=new MemberReqDto();

        reqDto.setName("이거 진짜 되나 테스트 #1");
        reqDto.setUserId("이거 진짜 id 1");
        reqDto.setNickname("fdsfd");
        reqDto.setPassword("12313");
        reqDto.setEmail("dfsfd@naver.com");
        reqDto.setPhone("010-3434-2323");

        //when
        Long saveId = memberService.join(reqDto, 1L);


        //then
    }

    @Test
    public void 회원조회_테스트(){
        //given
        Long id=13L;

        //when
        MemberResDto resDto = memberService.findMember(id);

        Member member = resDto.toEntity();


        //then
        System.out.println("resDto = " + resDto);

    }
}