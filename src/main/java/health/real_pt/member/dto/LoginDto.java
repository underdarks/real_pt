package health.real_pt.member.dto;

import lombok.Data;

//로그인용
//ID, PW 확인
@Data
public class LoginDto {
    private String userId;
    private String password;
}
