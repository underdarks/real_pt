package health.real_pt.member.repository;


import health.real_pt.member.domain.Member;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    /**
     * - 인터페이스 규칙 -
     * public abstract 메서드
     * public static final 변수
     * 생략 가능
     * <p>
     * 구현체 => MysqlMemberRepository.java
     */

    //등록, 수정
    Long save(Member member);

    //id로 조회
    Optional<Member> findById(Long memberId);

    //userid로 조회
    Optional<Member> findByUserId(String userId);

    List<Member> findByNameAndEmail(Member member);

    //전체 조회
    List<Member> findAll();

    //삭제
    void delete(Member member);

}


