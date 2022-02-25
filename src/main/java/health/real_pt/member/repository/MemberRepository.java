package health.real_pt.member.repository;


import health.real_pt.member.domain.Member;

import java.util.Optional;


public interface MemberRepository {
    /**
     *     - 인터페이스 규칙 -
     *     public abstract 메서드
     *     public static final 변수
     *     생략 가능
     */

    //등록, 수정
    Long save(Member member);

    //단일 조회
    Optional<Member> findById(Long memberId);

    Optional<Member> findByNameAndEmail(Member member);

    //삭제
    void delete(Member member);

}


