package health.real_pt.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDay;
    private String nickname;
    private String recommandCode;
    private String recommandedCode;

}
