package health.real_pt.member.dto;

import health.real_pt.common.BaseResDto;
import health.real_pt.member.domain.Member;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberResResDto implements BaseResDto<Member, MemberResResDto> {

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
    private String gymName;             //소속된 헬스장 이름


    @Override
    public MemberResResDto entityToDto(Member member) {
        MemberResResDto resDto = new MemberResResDto();

        resDto.setId(member.getId());
        resDto.setUserId(member.getUserId());
        resDto.setPassword("********");
        resDto.setName(member.getName());
        resDto.setEmail(member.getEmail());
        resDto.setPhone(member.getPhone());
        resDto.setBirthDay(member.getBirthDay());
        resDto.setNickname(member.getNickname());
        resDto.setRecommandCode(member.getRecommandCode());
        resDto.setRecommandedCode(member.getRecommandedCode());
        resDto.setGymName(member.getGym().getName());

        return resDto;
    }
}
