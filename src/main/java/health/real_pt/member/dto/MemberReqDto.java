package health.real_pt.member.dto;

import health.real_pt.common.BaseDto;
import health.real_pt.gym.domain.Gym;
import health.real_pt.member.domain.Member;
import health.real_pt.member.domain.MemberType;
import lombok.Data;

import java.time.LocalDate;


@Data
public class MemberReqDto {

    private String userId;          //uid
    private String password;        //pw
    private String name;            //이름
    private String email;           //이메일
    private String phone;           //전화번호
    private LocalDate birthDay;     //생일
    private String nickname;        //닉네임
    private MemberType memberType;  //멤버 타입
    private Gym gym;    //헬스장 PK


//    @Override
//    public MemberReqDto entityToDto(Member member) {
//        MemberReqDto memberReqDto = new MemberReqDto();
//
//        memberReqDto.setId(member.getId());
//        memberReqDto.setUserId(member.getUserId());
//        memberReqDto.setPassword("********");
//        memberReqDto.setName(member.getName());
//        memberReqDto.setEmail(member.getEmail());
//        memberReqDto.setPhone(member.getPhone());
//        memberReqDto.setBirthDay(member.getBirthDay());
//        memberReqDto.setNickname(member.getNickname());
//        memberReqDto.setGym(member.getGym());
//
//        return memberReqDto;
//    }

}
