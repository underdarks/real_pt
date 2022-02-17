package health.real_pt.member.repository;

import health.real_pt.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MysqlMemberRepository implements MemberRepository {

    private final EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        Member findMember = em.find(Member.class, memberId);
        return Optional.ofNullable(findMember);
    }

    @Override
    public Optional<Member> findByNameAndEmail() {
        return Optional.empty();
    }

    @Override
    public void delete(Member member) {
        em.remove(member);
    }
}
