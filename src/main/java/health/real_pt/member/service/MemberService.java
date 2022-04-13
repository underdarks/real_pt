package health.real_pt.member.service;

import health.real_pt.member.dto.MemberResResDto;
import health.real_pt.member.dto.MemberReqResDto;

import java.util.List;

public interface MemberService{

    /**
     * 회원가입
     * @param
     */
    Long join(MemberReqResDto memberReqDto, Long gymId);


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
    List<MemberResResDto> findAllMembers();

    /**
     * 단일 회원 찾기
     *
     */
    MemberResResDto findMember(Long id);

    /**
     * 회원 수정
     */
    MemberResResDto updateMember(Long id, MemberReqResDto memberReqDto);


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
