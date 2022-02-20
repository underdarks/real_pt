package health.real_pt.member.service;

import health.real_pt.member.domain.Member;
import health.real_pt.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional  //JPA는 트랜잭션 안에서 실행됨
public class MeberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void join(Member member) {
        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
        }
        finally {

        }
    }

    /**
     * 중복 회원 검증(Id로 찾기)
     *
     */
    private void validateDuplicateMember(Member member){
//        System.out.println("member = " + member.getId());
        Optional<Member> findMember = memberRepository.findByNameAndEmail(member);
        findMember.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        });
    }

    //
    @Override
    public void findID() {

    }

    @Override
    public void findPW() {

    }


}
