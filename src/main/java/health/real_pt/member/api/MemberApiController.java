package health.real_pt.member.api;


import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberDto;
import health.real_pt.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "회원 등록", notes = "신규 회원을 생성합니다.")
    @PostMapping("")
    public Long saveMember(@RequestBody @Valid MemberDto requestDto){
        return memberService.join(requestDto);
    }

    /**
     * 회원 수정
     */
    @ApiOperation(value = "회원 수정", notes = "id를 받아 회원 정보를 수정합니다.")
    @PatchMapping("/{id}")
    public MemberDto updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberDto requestDto){
        memberService.updateMember(id, requestDto);
        Optional<Member> memberOptional = memberService.findMember(id);

        Member member = memberOptional.orElseThrow();

        return new MemberDto().entityToDto(member);
    }

    /**
     * 모든 회원 조회
     */
    @ApiOperation(value = "모든 회원 조회", notes = "모든 회원을 조회합니다.")
    @GetMapping("/members")
    public MemberResDto findAllMembers(){
        List<Member> findMembers = memberService.findAllMembers();

        List<MemberDto> memberDtoList = findMembers.stream()
                .map(m -> new MemberDto().entityToDto(m))
                .collect(Collectors.toList());

        return new MemberResDto(memberDtoList.size(),memberDtoList);
    }

    /**
     * 단일 회원 조회
     */
    @ApiOperation(value = "단일 회원 조회", notes = "id를 받아 회원을 조회합니다." )
    @GetMapping("/{id}")
    public MemberDto findMember(@PathVariable("id") Long id){
        Optional<Member> findMember = memberService.findMember(id);
        Member member = findMember.orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다!"));

        return new MemberDto().entityToDto(member);
    }

    /**
     * 회원 삭제
     */
    @ApiOperation(value = "회원 삭제", notes = "id를 받아 회원을 삭제합니다.")
    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable("id") Long id){
        memberService.quit(id);

        return "success";
    }


}

