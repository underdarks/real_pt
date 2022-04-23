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
    @Builder.Default
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
                  String nickname, String recommandCode,
                  String recommandedCode, MemberType memberType,
                  Gym gym) {

        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDay = birthDay;
        this.nickname = nickname;
        this.memberType = memberType;
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

