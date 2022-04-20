package health.real_pt.member.dto;

import health.real_pt.common.BaseDto;
import health.real_pt.gym.domain.Gym;
import health.real_pt.member.domain.Member;
import health.real_pt.member.domain.MemberType;
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
    private MemberType memberType;
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
        memberReqDto.setGym(member.getGym());

        return memberReqDto;
    }

}
