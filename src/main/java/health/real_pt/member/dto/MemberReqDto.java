package health.real_pt.member.dto;

import health.real_pt.common.BaseDto;
import health.real_pt.gym.domain.Gym;
import health.real_pt.member.domain.Member;
import lombok.Data;

import java.time.LocalDate;


@Data
public class MemberReqDto implements BaseDto<Member, MemberReqDto> {

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
    public MemberReqDto entityToDto(Member member) {
        MemberReqDto memberReqDto = new MemberReqDto();

        memberReqDto.setId(member.getId());
        memberReqDto.setUserId(member.getUserId());
        memberReqDto.setPassword("********");
        memberReqDto.setName(member.getName());
        memberReqDto.setEmail(member.getEmail());
        memberReqDto.setPhone(member.getPhone());
        memberReqDto.setBirthDay(member.getBirthDay());
        memberReqDto.setNickname(member.getNickname());
        memberReqDto.setRecommandCode(member.getRecommandCode());
        memberReqDto.setRecommandedCode(member.getRecommandedCode());
        memberReqDto.setGym(member.getGym());

        return memberReqDto;
    }

}
