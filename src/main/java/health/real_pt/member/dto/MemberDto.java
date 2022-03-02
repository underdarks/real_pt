package health.real_pt.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberDto {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDay;
    private String nickname;
    private String recommandCode;       //추천인 코드(내가 상대방 추천인코드 적을 때)
    private String recommandedCode;     //추천인 코드(상대방이 내추천인코드 적을 때)

}
