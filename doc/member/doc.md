## 회원 관리
헬스 트레이너, 헬스장 회원, 헬스장 대표 등 헬스장 관련된 전반적인 회원들을 관리합니다.
<br>
<br>

<h2>회원 엔티티 </h2>
아래 코드는 회원 엔티티이다. <br>
다른 필드는 크게 설명할것은 없어보인다. 맨 마지막에 있는 Gym 필드는 Gym엔티티의 PK로 1:1 관계를 가진다.(<Strong>@OneToOne</Strong>)<br><br>

~~~java
package health.real_pt.member.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.domain.Gym;
import health.real_pt.member.dto.MemberReqResDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;

@Entity @Table(name = "MEMBER") 
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class Member extends BaseTimeEntity implements BaseEntity<MemberReqDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @NotBlank(message = "ID는 필수 값입니다!")
    @Column(name = "USER_ID", unique = true)
    private String userId;

    @NotBlank(message = "비밀번호는 필수 값입니다!")
    @Column(name = "PASSWORD")
    private String password;

    @NotBlank(message = "이름은 필수 값입니다!")
    @Column(name = "NAME")
    private String name;

    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "BIRTHDAY")
    private LocalDate birthDay;

    @NotBlank(message = "닉네임은 필수 값입니다!")
    @Column(name = "NICKNAME", unique = true)
    private String nickname;

    @Column(name = "RECOMMAND_CODE")    //추천인 코드(내가 상대방 추천인코드 적을 때)
    private String recommandCode;

    @Column(name = "RECOMMANDED_CODE")  //추천인 코드(상대방이 내추천인코드 적을 때)
    private String recommandedCode;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "GYM_ID")
    private Gym gym;

    /**
     * setter 대신 도메인 객체 변경하는 메서드들(setter 사용 지양)
     */

    public void changePW(String password) {
        this.password = password;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeRecommandCode(String recommandCode) {
        this.recommandCode = recommandCode;
    }

    public void changeRecommandedCode(String recommandedCode) {
        this.recommandedCode = recommandedCode;
    }

    public void changeGym(Gym gym) {
        this.gym = gym;
    }

    /* ============================================================================================================== */

    //객체 생성 빌더 패턴
    @Builder
    public Member(String userId, String password,
                  String name, String email,
                  String phone, LocalDate birthDay,
                  String nickname, String recommandCode,
                  String recommandedCode,Gym gym) {

        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDay = birthDay;
        this.nickname = nickname;
        this.recommandCode = recommandCode;
        this.recommandedCode = recommandedCode;
        this.gym=gym;
    }

    //DTO -> Entity로 변환
    public static Member toEntity(MemberReqDto memberReqDto) {
        return Member.builder()
                .userId(memberReqDto.getUserId())
                .password(memberReqDto.getPassword())
                .name(memberReqDto.getName())
                .email(memberReqDto.getEmail())
                .phone(memberReqDto.getPhone())
                .birthDay(memberReqDto.getBirthDay())
                .nickname(memberReqDto.getNickname())
                .recommandCode(memberReqDto.getRecommandCode())
                .recommandedCode(memberReqDto.getRecommandedCode())
                .gym(memberReqDto.getGym())
                .build();

    }

    //엔티티 수정을 위한 공통 메서드
    @Override
    public void updateEntity(MemberReqDto memberReqDto) {
        if (memberReqDto.getGym() != null)
            changeGym(memberReqDto.getGym());

        if (memberReqDto.getEmail() != null)
            changeEmail(memberReqDto.getEmail());

        if (memberReqDto.getNickname() != null)
            changeNickname(memberReqDto.getNickname());

        if (memberReqDto.getPassword() != null)
            changePW(memberReqDto.getPassword());

        if (memberReqDto.getPhone() != null)
            changePhone(memberReqDto.getPhone());
    }
}




~~~


<br>
<h2>회원 API Controller</h2>


~~~java
package health.real_pt.member.api;


import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.service.GymService;
import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberReqResDto;
import health.real_pt.member.dto.MemberListDto;
import health.real_pt.member.dto.MemberResResDto;
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
PATCH 방식으로 요청하여(http://localhost:8080/api/v1/member/id) uri에 id(pk)와 HttpBody에 수정할 내용을 넘겨 회원 정보를 수정한다.<br>
Response는 수정된 정보가 반한된다<br><br>

![회원 수정](https://user-images.githubusercontent.com/41244406/162107477-37e722bd-9784-4156-b73e-031a9e70ba73.PNG)


----------------------------------
<h3>회원 탈퇴</h3>
DELETE 방식으로 요청하여(http://localhost:8080/api/v1/member/id) uri에 id를 넘겨 회원 탈퇴를 한다.<br><br>

![회원 삭제](https://user-images.githubusercontent.com/41244406/162109122-93fb24c4-7dac-41f1-a6be-4ead7aff6091.PNG)


<br>
<h2>API 문서 관리</h2>
swaager를 활용하여 API 문서 관리 자동화<br><br>

![swagger](https://user-images.githubusercontent.com/41244406/161994284-f5e6226e-8c96-40eb-9e45-208e8a93fb9d.PNG)



