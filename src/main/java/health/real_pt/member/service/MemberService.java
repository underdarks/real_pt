package health.real_pt.member.service;

import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberDto;
import health.real_pt.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    /**
     * 회원가입
     * @param
     */
    Long join(MemberDto memberDto);


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
     */
    List<Member> findAllMembers();

    /**
     * 단일 회원 찾기
     *
     */
    Optional<Member> findMember(Long memberId);

    /**
     * 회원 수정
     */
    void updateMember(Long memberId,MemberDto memberDto);


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
