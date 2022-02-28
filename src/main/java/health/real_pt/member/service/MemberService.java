package health.real_pt.member.service;

import health.real_pt.member.domain.Member;
import health.real_pt.member.repository.MemberRepository;

public interface MemberService {

    /**
     * 회원가입
     * @param member
     */
    void join(Member member);


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
     * SNS 로그인 연동 예정(카카오, 페이스북, 인스타 등)
     *
     */


    /**
     * 회원 탈퇴
     * @param id
     */
    void quit(Long id);
}
