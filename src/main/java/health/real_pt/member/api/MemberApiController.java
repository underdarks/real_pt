package health.real_pt.member.api;


import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberDto;
import health.real_pt.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/member")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원 등록
     */
    @PostMapping("")
    public Long saveMember(@RequestBody @Valid MemberDto reqMemberDto){
        return memberService.join(reqMemberDto);
    }

    /**
     * 회원 수정
     */
    @PatchMapping("/{id}")
    public MemberDto updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberDto updMemberDto){
        memberService.updateMember(id, updMemberDto);
        Optional<Member> memberOptional = memberService.findMember(id);

        Member member = memberOptional.orElseThrow();

        return new MemberDto().entityToDto(member);
    }

    /**
     * 모든 회원 조회
     */
    @GetMapping("/members")
    public MemberResultDto findAllMembers(){
        List<Member> findMembers = memberService.findAllMembers();

        List<MemberDto> memberDtoList = findMembers.stream()
                .map(m -> new MemberDto().entityToDto(m))
                .collect(Collectors.toList());

        return new MemberResultDto(memberDtoList.size(),memberDtoList);
    }

    /**
     * 단일 회원 조회
     */
    @GetMapping("/{id}")
    public MemberDto findMember(@PathVariable("id") Long id){
        Optional<Member> findMember = memberService.findMember(id);
        Member member = findMember.orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다!"));

        return new MemberDto().entityToDto(member);
    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable("id") Long id){
        memberService.quit(id);

        return "success";
    }


}

