package health.real_pt.member.domain;

import health.real_pt.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "MEMBER")
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USER_ID",unique = true)
    private String userId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL",unique = true)
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "BIRTHDAY")
    private LocalDate birthDay;

    @Column(name = "NICKNAME", unique = true)
    private String nickname;

    @Column(name = "RECOMMAND_CODE")    //추천인 코드(내가 상대방 추천인코드 적을 때)
    private String recommandCode;

    @Column(name = "RECOMMANDED_CODE")  //추천인 코드(상대방이 내추천인코드 적을 때)
    private String recommandedCode;

}
