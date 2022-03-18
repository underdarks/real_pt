package health.real_pt.member.api;


import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberDto;
import health.real_pt.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberService memberService;

    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원 등록 API
     *
     */
    @PostMapping("")
    public MemberDto saveMember(@RequestBody @Valid MemberDto reqMemberDto){
        memberService.join(reqMemberDto);
        return reqMemberDto;
    }

    /**
     * 모든 회원 조회 API
     */
    @GetMapping("/members")
    public MemberResultVo findAllMembers(){
        List<Member> findMembers = memberService.findAllMembers();

        List<MemberDto> memberDtoList = findMembers.stream()
                .map(m -> new MemberDto().entityToDto(m))
                .collect(Collectors.toList());

        return new MemberResultVo(memberDtoList.size(),memberDtoList);
    }

    /**
     * 단일 회원 조회 API
     */





}

