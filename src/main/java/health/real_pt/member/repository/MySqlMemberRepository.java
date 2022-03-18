package health.real_pt.member.repository;

import health.real_pt.member.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
//@RequiredArgsConstructor
public class MySqlMemberRepository implements MemberRepository {

    private final EntityManager em;

    public MySqlMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        Member findMember = em.find(Member.class, memberId);
        return Optional.ofNullable(findMember);
    }

    @Override
    public List<Member> findByNameAndEmail(Member member) {
        List resultList = em.createQuery("select m from Member m where m.name =: name and m.email =:email").
                setParameter("name", member.getName()).
                setParameter("email", member.getEmail()).getResultList();


        return resultList;
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m").getResultList();
    }

    @Override
    public void delete(Member member) {
        em.remove(member);
    }



}
