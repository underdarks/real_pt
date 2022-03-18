package health.real_pt.member.dto;

import health.real_pt.common.BaseDto;
import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.domain.Gym;
import health.real_pt.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Data
public class MemberDto extends BaseTimeEntity implements BaseDto<Member,MemberDto> {

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
    private Gym gym;    //헬스장 ID


    @Override
    public MemberDto entityToDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setUserId(member.getUserId());
        memberDto.setPassword("********");
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        memberDto.setPhone(member.getPhone());
        memberDto.setBirthDay(member.getBirthDay());
        memberDto.setNickname(member.getNickname());
        memberDto.setRecommandCode(member.getRecommandCode());
        memberDto.setRecommandedCode(member.getRecommandedCode());
        memberDto.setGym(member.getGym());

        return memberDto;
    }
}
