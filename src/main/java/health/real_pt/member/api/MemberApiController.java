package health.real_pt.member.api;


import health.real_pt.common.CommonResDto;
import health.real_pt.common.ResponseMessage;
import health.real_pt.common.StatusCode;
import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.service.GymService;
import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberReqDto;
import health.real_pt.member.dto.MemberListDto;
import health.real_pt.member.dto.MemberResDto;
import health.real_pt.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/member")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final GymService gymService;

    /**
     * 회원 등록
     */
    @ApiOperation(value = "회원 등록", notes = "신규 회원을 생성합니다.")
    @PostMapping("")
    public ResponseEntity saveMember(@RequestHeader(value = "gym-id") Long gymId, @RequestBody @Valid MemberReqDto requestDto){
        Gym gym = gymService.findOne(gymId);
        requestDto.setGym(gym);

        return new ResponseEntity(
                CommonResDto.createResponse(StatusCode.CREATED, ResponseMessage.CREATED_USER_SUCCESS),
                HttpStatus.OK
        );
    }

    /**
     * 회원 수정
     */
    @ApiOperation(value = "회원 수정", notes = "id를 받아 회원 정보를 수정합니다.")
    @PatchMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberReqDto requestDto){
        MemberResDto memberResDto = memberService.updateMember(id, requestDto);

        return new ResponseEntity(
                CommonResDto.createResponse(StatusCode.CREATED,ResponseMessage.UPDATE_USER_SUCCESS),
                HttpStatus.OK
        );

    }

    /**
     * 모든 회원 조회
     */
    @ApiOperation(value = "모든 회원 조회", notes = "모든 회원을 조회합니다.")
    @GetMapping("")
    public MemberListDto findAllMembers(){
        List<MemberResDto> resDtoList = memberService.findAllMembers();
        return new MemberListDto(resDtoList.size(),resDtoList);
    }

    /**
     * 단일 회원 조회
     */
    @ApiOperation(value = "단일 회원 조회", notes = "id를 받아 회원을 조회합니다." )
    @GetMapping("/{id}")
    public ResponseEntity findMember(@PathVariable("id") Long id){
        return memberService.findMember(id);
    }

    /**
     * 회원 삭제
     */
    @ApiOperation(value = "회원 삭제", notes = "id를 받아 회원을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable("id") Long id){
        memberService.quit(id);

        return "success";
    }


}

