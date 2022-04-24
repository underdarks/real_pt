package health.real_pt.member.api;


import health.real_pt.exception.exception_handler.ExceptionType;
import health.real_pt.exception.exceptions.CommonApiExceptions;
import health.real_pt.common.response.CommonResEntity;
import health.real_pt.common.response.CommonResMessage;
import health.real_pt.common.response.StatusCode;
import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.LoginDto;
import health.real_pt.member.dto.MemberResDto;
import health.real_pt.member.dto.MemberReqDto;
import health.real_pt.member.dto.MemberListDto;
import health.real_pt.member.service.MemberService;
import health.real_pt.security.config.JwtTokenProvider;
import health.real_pt.security.encryption.SHA256;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/member")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    /**
     *  회원 등록 필수 값 확인 처리
     */
    public void checkReqDtoValidation(MemberReqDto reqDto){
        if(reqDto.getUserId().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL,"userId는 필수 값입니다.");

        else if(reqDto.getPassword().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL,"password는 필수 값입니다.");

        else if(reqDto.getName().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL,"name은 필수 값입니다.");

        else if(reqDto.getEmail().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL,"email은 필수 값입니다.");

        else if(reqDto.getPhone().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL,"phone은 필수 값입니다.");

        else if(reqDto.getNickname().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL,"nickname은 필수 값입니다.");

    }

    /**
     * 로그인
     */
    @ApiOperation(value = "로그인", notes = "사용자 정보 확인 후 로그인 합니다.")
    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto loginDto){
        Member member = memberService.login(loginDto);

//        if(passwordEncoder.matches(SHA256.getSHA256HashCode(loginDto.getPassword()), member.getPassword()))
//            throw new IllegalArgumentException("잘못된 비밀번호 입니다");

        return jwtTokenProvider.createJwtToken(member.getUserId(),member.getRoles());
    }


    /**
     * 회원 등록
     */
    @ApiOperation(value = "회원 등록", notes = "신규 회원을 생성합니다.")
    @PostMapping("")
    public ResponseEntity<CommonResEntity> saveMember(
            @RequestPart(value = "reqData") MemberReqDto reqDto,
            @RequestPart(value = "images") List<MultipartFile> files,
            @RequestParam(value = "gym-id" ) Long gymId){

        checkReqDtoValidation(reqDto);
        memberService.join(reqDto,gymId,files);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.CREATED, CommonResMessage.CREATED_USER_SUCCESS),
                HttpStatus.CREATED
        );
    }

    /**
     * 회원 수정
     */
    @ApiOperation(value = "회원 수정", notes = "id를 받아 회원 정보를 수정합니다.")
    @PatchMapping("/{id}")
    public ResponseEntity<CommonResEntity> updateMember(
            @RequestPart(value = "reqData") MemberReqDto udpDto,
            @RequestPart(value = "images") List<MultipartFile> files,
            @RequestParam(value = "gym-id" ) Long gymId,
            @PathVariable("id") Long id){
        MemberResDto memberResDto = memberService.updateMember(id, gymId,udpDto,files);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.UPDATE_USER_SUCCESS,memberResDto),
                HttpStatus.OK
        );

    }

    /**
     * 모든 회원 조회
     */
    @ApiOperation(value = "전체 회원 조회", notes = "전체 회원을 조회합니다.")
    @GetMapping("")
    public ResponseEntity<CommonResEntity> findAllMembers(){
        List<MemberResDto> resDtoList = memberService.findAllMembers();
        MemberListDto memberListDto = new MemberListDto(resDtoList.size(), resDtoList);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.READ_ALL_USER_SUCCESS,memberListDto),
                HttpStatus.OK
        );
    }

    /**
     * 단일 회원 조회
     */
    @ApiOperation(value = "단일 회원 조회", notes = "id를 받아 회원을 조회합니다." )
    @GetMapping("/{id}")
    public ResponseEntity<CommonResEntity> findMember(@PathVariable("id") Long id){
        MemberResDto resDto = memberService.findMember(id);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.READ_USER_SUCCESS,resDto),
                HttpStatus.OK
        );
    }

    /**
     * 회원 삭제
     */
    @ApiOperation(value = "회원 삭제", notes = "id를 받아 회원을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResEntity> deleteMember(@PathVariable("id") Long id){
        memberService.quit(id);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.DELETE_USER_SUCCESS),
                HttpStatus.OK
        );
    }


}

