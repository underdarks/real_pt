## 회원과 관련된 주요 코드들만 모아놓은 문서입니다.
상세 코드를 보고 싶으시면 repo Clone 하셔서 보시면됩니다.



### 회원 엔티티
아래 코드는 회원 엔티티이다.<br>
회원은 Gym과, MemberImage, ptReview, ptPrice, memberRoles와 1:N(@OnetoMany) 관계를 가진다.

~~~java
package health.real_pt.member.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.domain.Gym;
import health.real_pt.image.domain.MemberImage;
import health.real_pt.member.dto.MemberReqDto;
import health.real_pt.price.domain.PtPrice;
import health.real_pt.review.domain.PtReview;
import health.real_pt.security.encryption.SHA256;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
public class Member extends BaseTimeEntity implements BaseEntity<MemberReqDto>, UserDetails {   //SpringSecurity는 UserDetails 객체를 통해 권한 정보 관리하기 때문에 UserDetails 상속 후 재정의 해야한다
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

    @NotBlank(message = "이메일은 필수 값입니다!")
    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotBlank(message = "전화번호는 필수 값입니다!")
    @Column(name = "PHONE")
    private String phone;

    @Column(name = "BIRTHDAY")
    private LocalDate birthDay;

    @NotBlank(message = "닉네임은 필수 값입니다!")
    @Column(name = "NICKNAME", unique = true)
    private String nickname;


    //Enum타입은 꼭 String으로 써라 Ordinal은 2가지 값만 갖는다. 따라서 확장 안됨
    @NotNull(message = "회원 타입은 필수 값입니다!")
    @Enumerated(EnumType.STRING)
    private MemberType memberType;      //회원 타입

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "GYM_ID")
    private Gym gym;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST, orphanRemoval = true) //멤버 삭제시 사진 같이 삭제
    private List<MemberImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "pt", orphanRemoval = true)     //PT 삭제시 리뷰 같이 삭제
    private List<PtReview> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "pt",cascade = CascadeType.PERSIST, orphanRemoval = true)     //PT 삭제시 가격 같이 삭제
    private List<PtPrice> prices=new ArrayList<>();

    @ElementCollection(fetch = EAGER)
//    @Builder.Default
    private List<String> roles=new ArrayList<>();

    //=============== UserDetails 상속 ====================
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return userId;  //Spring Security에서 사용하는 userName을 userId로 설정
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

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

    //============= 연관관계 편의 메서드 =========================

    //헬스장 - 멤버 연결
    public void addGym(Gym gym) {
        this.gym = gym;
        this.gym.getPt().add(this);
    }

    //Pt가 속한 헬스장 연관관계 해제
   public void deleteGym(){
        this.gym=null;
    }

    //멤버 - 이미지 삭제(고아 객체 자동 삭제)
    public void deleteMemberImages() {
        int size = images.size();

        for (int i = 0; i < size; i++)
            this.images.remove(0);
    }

    /* ============================================================================================================== */

    //객체 생성 빌더 패턴
    @Builder
    public Member(String userId, String password,
                  String name, String email,
                  String phone, LocalDate birthDay,
                  String nickname, MemberType memberType,
                  Gym gym,List<String> roles) {

        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDay = birthDay;
        this.nickname = nickname;
        this.memberType = memberType;
        this.roles=roles;
        addGym(gym);
    }

    //DTO -> Entity로 변환
    public static Member toEntity(MemberReqDto memberReqDto) {
        return Member.builder()
                .userId(memberReqDto.getUserId())
                .password(SHA256.getSHA256HashCode(memberReqDto.getPassword()))    //비밀번호 SHA256 암호화
                .name(memberReqDto.getName())
                .email(memberReqDto.getEmail())
                .phone(memberReqDto.getPhone())
                .birthDay(memberReqDto.getBirthDay())
                .nickname(memberReqDto.getNickname())
                .memberType(memberReqDto.getMemberType())
                .roles(Collections.singletonList("ROLE_USER"))   //최초 가입시 USER 권한(꼭, DB 저장시 ROLE_이 붙어야함
                .gym(memberReqDto.getGym())
                .build();

    }

    @Override
    public void updateEntity(MemberReqDto memberReqDto) {
        if (memberReqDto.getGym() != null)
            addGym(memberReqDto.getGym());

        if (memberReqDto.getEmail() != null)
            changeEmail(memberReqDto.getEmail());

        if (memberReqDto.getNickname() != null)
            changeNickname(memberReqDto.getNickname());

        if (memberReqDto.getPassword() != null)
            changePW(SHA256.getSHA256HashCode(memberReqDto.getPassword()));

        if (memberReqDto.getPhone() != null)
            changePhone(memberReqDto.getPhone());
    }


}


~~~

### 회원 API Controller
~~~ java
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
        if(reqDto.getUserId() == null || reqDto.getUserId().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL,"userId는 필수 값입니다.");

        else if(reqDto.getPassword() == null || reqDto.getPassword().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL,"password는 필수 값입니다.");

        else if(reqDto.getName() == null ||reqDto.getName().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL,"name은 필수 값입니다.");

        else if(reqDto.getEmail() == null ||reqDto.getEmail().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL,"email은 필수 값입니다.");

        else if(reqDto.getPhone() == null ||reqDto.getPhone().isBlank())
            throw new CommonApiExceptions(ExceptionType.PARAMETER_VALUE_ILLEGAL,"phone은 필수 값입니다.");

        else if(reqDto.getNickname() == null ||reqDto.getNickname().isBlank())
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

~~~

