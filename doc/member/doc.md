## 회원 관리
<Strong>헬스 트레이너, 헬스장 회원, 헬스장 대표 등 헬스장 관련된 전반적인 회원들을 관리합니다.</Strong>
<br>
<br>

<h2>회원 엔티티 </h2>
아래 이미지는 회원 엔티티이다. <br>
다른 필드는 크게 설명할것은 없어보인다. 맨 마지막에 있는 Gym 필드는 Gym엔티티의 PK로 1:1 관계를 가진다.(@OneToOne)<br><br>

![entity](https://user-images.githubusercontent.com/41244406/161987466-106ffc5e-924a-49ff-8a42-1dbd32db9dab.PNG)


<br>
<h2>회원 API Controller</h2>


~~~java
package health.real_pt.member.api;


import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.service.GymService;
import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberReqDto;
import health.real_pt.member.dto.MemberListDto;
import health.real_pt.member.dto.MemberResDto;
import health.real_pt.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    public Long saveMember(@RequestHeader(value = "gym-id") Long gymId,@RequestBody @Valid MemberReqDto requestDto){
        Gym gym = gymService.findOne(gymId);
        requestDto.setGym(gym);

        return memberService.join(requestDto);
    }

    /**
     * 회원 수정
     */
    @ApiOperation(value = "회원 수정", notes = "id를 받아 회원 정보를 수정합니다.")
    @PatchMapping("/{id}")
    public MemberResDto updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberReqDto requestDto){
        return memberService.updateMember(id, requestDto);
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
    public MemberResDto findMember(@PathVariable("id") Long id){
        return memberService.findMember(id);
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

~~~

<br>
<h2>API 기능</h2>

- #### 회원 가입
- #### 아이디 찾기    (Email을 활용한 아이디 찾기)
- #### 비밀번호 찾기  (아이디 + Email을 활용한 비밀번호 찾기)
- #### 회원 수정 
- #### 회원 조회     
- #### 회원 탈퇴

<br>
<h2>API 상세 설명 및 테스트 </h2>
 
<h3>회원 가입</h3>

HttpHeader값에 member엔티티에 있는 Gym필드 값을 담아서 전송한다.<br>

![회원등록 헤더](https://user-images.githubusercontent.com/41244406/161992486-fddd2cff-2f62-4eec-91d9-7866bf40c8f4.PNG)

<br>
회원 정보 입력 후 Post(http://localhost:8080/api/v1/member)로 요청하여 회원 가입을 한다.<br>
Response값은 PK값을 반환한다.<br><br>

![회원등록](https://user-images.githubusercontent.com/41244406/161994318-47335e27-4228-4686-a80b-2117edcb339a.PNG)


<h3>회원 등록 API Controller</h3>

~~~java

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
    public Long saveMember(@RequestHeader(value = "gym-id") Long gymId,@RequestBody @Valid MemberReqDto requestDto){
        Gym gym = gymService.findOne(gymId);
        requestDto.setGym(gym);

        return memberService.join(requestDto);
    }

}


~~~
<br>
----------------------------------

<h3>회원 조회</h3>

<h4>전체 회원 조회</h4>
Get방식으로(http://localhost:8080/api/v1/member) 요청하여 모든 회원을 조회한다.<br><br>

![회원 전체 조회](https://user-images.githubusercontent.com/41244406/162015320-660fade4-9577-4eaa-9d54-9631ac18fb27.PNG)


<h4>단일 회원 조회</h4>
Get 방식으로(http://localhost:8080/api/v1/member/id) uri에 id(pk)를 같이 넘겨 회원을 조회한다.<br><br>

![단일 회원 조회](https://user-images.githubusercontent.com/41244406/162016767-1fb7a4b3-185d-42b4-bd1c-3eafb2f95446.PNG)



----------------------------------

<h3>회원 수정</h3>



----------------------------------
<h3>회원 삭제</h3>


<br>
<h2>API 문서 관리</h2>
swaager를 활용하여 API 문서 관리 자동화<br><br>

![swagger](https://user-images.githubusercontent.com/41244406/161994284-f5e6226e-8c96-40eb-9e45-208e8a93fb9d.PNG)



