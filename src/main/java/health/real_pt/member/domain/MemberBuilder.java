package health.real_pt.member.domain;

import health.real_pt.common.CommonBuilder;
import health.real_pt.member.dto.UserDto;
import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *  Member 엔티티의 Builder 클래스
 */
@Getter
public class MemberBuilder implements CommonBuilder<Member> {

    private final Long id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
    private final String phone;
    private final LocalDate birthDay;
    private final String nickname;
    private final String recommandCode;
    private final String recommandedCode;


    public MemberBuilder(UserDto userDto) {
        this.id = userDto.getId();
        this.userId = userDto.getUserId();
        this.password = userDto.getPassword();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.phone = userDto.getPhone();
        this.birthDay = userDto.getBirthDay();
        this.nickname = userDto.getNickname();
        this.recommandCode = userDto.getRecommandCode();
        this.recommandedCode = userDto.getRecommandedCode();
    }

    @Override
    public Member build() {
        return new Member(this);
    }
}
