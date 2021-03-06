package health.real_pt.member.service;

import health.real_pt.common.BaseInterface;
import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.LoginDto;
import health.real_pt.member.dto.MemberResDto;
import health.real_pt.member.dto.MemberReqDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberService extends BaseInterface<Member> {

    /**
     * 로그인
     */
    Member login(LoginDto loginDto);



    /**
     * 회원가입
     * @param
     */
    Long join(MemberReqDto memberReqDto, Long gymId,List<MultipartFile> files);


    /**
     * 아이디 찾기 (휴대폰 인증 연동 예정)
     *
     */
    void findID();


    /**
     *  비밀번호 찾기 (휴대폰 인증 연동 예정)
     *
     */
    void findPW();

    /**
     * 모든 회원 찾기
     *
     * @return
     */
    List<MemberResDto> findAllMembers();

    /**
     * 단일 회원 찾기
     *
     */
    MemberResDto findMember(Long id);

    /**
     * 회원 수정
     */
    MemberResDto updateMember(Long id, Long gymId, MemberReqDto udpDto, List<MultipartFile> files);


    /**
     * SNS 로그인 연동 예정(카카오, 페이스북, 인스타 등)
     *
     */


    /**
     * 회원 탈퇴
     * @param id
     */
    void quit(Long id);
}
