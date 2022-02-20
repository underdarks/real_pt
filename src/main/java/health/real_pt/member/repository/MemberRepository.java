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

    Member save(Member member);

    Optional<Member> findById(Long memberId);

    Optional<Member> findByNameAndEmail(Member member);

    void delete(Member member);

}


