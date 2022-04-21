package health.real_pt.member.dto;

import health.real_pt.common.BaseDto;
import health.real_pt.image.domain.MemberImage;
import health.real_pt.image.dto.ImageResDto;
import health.real_pt.member.domain.Member;
import health.real_pt.member.domain.MemberType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MemberResDto {

    private Long id;
    private String userId;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDay;
    private String nickname;
    private MemberType memberType;
    private String gymName;             //소속된 헬스장 이름
    private List<ImageResDto> imageList;    //프로필 이미지 리스트


    public MemberResDto entityToDto(Member member, List<ImageResDto> images) {
        MemberResDto resDto = new MemberResDto();

        resDto.setId(member.getId());
        resDto.setUserId(member.getUserId());
        resDto.setName(member.getName());
        resDto.setEmail(member.getEmail());
        resDto.setPhone(member.getPhone());
        resDto.setBirthDay(member.getBirthDay());
        resDto.setNickname(member.getNickname());
        resDto.setMemberType(member.getMemberType());
        resDto.setGymName(member.getGym() == null ? "소속된 헬스장 없음" : member.getGym().getName());

        resDto.setImageList(images);

        return resDto;
    }

}
