package health.real_pt.member.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.domain.Gym;
import health.real_pt.member.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity @Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class Member extends BaseTimeEntity implements BaseEntity<MemberDto>{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @NotNull(message = "ID는 Null이 될 수 없습니다!")
    @Column(name = "USER_ID",unique = true)
    private String userId;

    @NotNull(message = "비밀번호는 Null이 될 수 없습니다!")
    @Column(name = "PASSWORD")
    private String password;

    @NotNull(message = "이름은 Null이 될 수 없습니다!")
    @Column(name = "NAME")
    private String name;

    @NotNull(message = "메일은 Null이 될 수 없습니다!")
    @Column(name = "EMAIL",unique = true)
    private String email;

    @Column(name = "PHONE")
    private String phone;

    //    @Column(name = "BIRTHDAY",nullable = false)
//    @NotNull(message = "생일은 Null이 될 수 없습니다!")
    @Column(name = "BIRTHDAY")
    private LocalDate birthDay;

    @NotNull(message = "닉네임은 Null이 될 수 없습니다!")
    @Column(name = "NICKNAME", unique = true)
    private String nickname;

    @Column(name = "RECOMMAND_CODE")    //추천인 코드(내가 상대방 추천인코드 적을 때)
    private String recommandCode;

    @Column(name = "RECOMMANDED_CODE")  //추천인 코드(상대방이 내추천인코드 적을 때)
    private String recommandedCode;

    @OneToOne
    @JoinColumn(name = "GYM_ID")
    private Gym gym;

    /**
     *  setter 대신 도메인 객체 변경하는 메서드들(setter 사용 지양)
     */

    public void changePW(String password){
        if(password != null && !password.isEmpty())
            this.password=password;
    }

    public void changeEmail(String email){
        if(email != null && !email.isEmpty())
            this.email=email;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeNickname(String nickname) {
        if(nickname != null && !nickname.isEmpty())
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
    public Member(String userId, String password, String name, String email, String phone, LocalDate birthDay, String nickname, String recommandCode, String recommandedCode) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDay = birthDay;
        this.nickname = nickname;
        this.recommandCode = recommandCode;
        this.recommandedCode = recommandedCode;
    }

    //DTO -> Entity로 변환
    public static Member toEntity(MemberDto memberDto){
        return Member.builder()
                .userId(memberDto.getUserId())
                .password(memberDto.getPassword())
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .birthDay(memberDto.getBirthDay())
                .nickname(memberDto.getNickname())
                .recommandCode(memberDto.getRecommandCode())
                .recommandedCode(memberDto.getRecommandedCode())
                .build();

    }

    @Override
    public void updateEntity(MemberDto memberDto) {
        changeGym(memberDto.getGym());
        changeEmail(memberDto.getEmail());
        changeNickname(memberDto.getNickname());
        changePW(memberDto.getPassword());
        changePhone(memberDto.getPhone());
    }
}

