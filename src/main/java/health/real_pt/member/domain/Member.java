package health.real_pt.member.domain;

import health.real_pt.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "EMAIL",unique = true)
    private String email;

    @Column(name = "PHONE")
    private String phone;

    //    @Column(name = "BIRTHDAY",nullable = false)
    @NotNull(message = "생일은 Null이 될 수 없습니다!")
    @Column(name = "BIRTHDAY")
    private LocalDate birthDay;

    @Column(name = "NICKNAME", unique = true)
    private String nickname;

    @Column(name = "RECOMMAND_CODE")    //추천인 코드(내가 상대방 추천인코드 적을 때)
    private String recommandCode;

    @Column(name = "RECOMMANDED_CODE")  //추천인 코드(상대방이 내추천인코드 적을 때)
    private String recommandedCode;

    //변수 값을 통한 객체 생성 빌더 패턴
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

    //클래스 빌더를 통한 객체 생성
    public Member(health.real_pt.member.domain.MemberBuilder memberBuilder){
        this.id = memberBuilder.getId();
        this.userId = memberBuilder.getUserId();
        this.password = memberBuilder.getPassword();
        this.name = memberBuilder.getName();
        this.email = memberBuilder.getEmail();
        this.phone = memberBuilder.getPhone();
        this.birthDay = memberBuilder.getBirthDay();
        this.nickname = memberBuilder.getNickname();
        this.recommandCode = memberBuilder.getRecommandCode();
        this.recommandedCode = memberBuilder.getRecommandedCode();

    }

}

