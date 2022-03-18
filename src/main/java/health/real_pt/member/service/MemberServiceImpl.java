package health.real_pt.member.service;

import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberDto;
import health.real_pt.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
@Transactional  //JPA는 트랜잭션 안에서 실행됨
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Long join(MemberDto memberDto) {
        try {
            Member member = Member.toEntity(memberDto);

            validateDuplicateMember(member);
            return  memberRepository.save(member);
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
        List<Member> memberList = memberRepository.findByNameAndEmail(member);

        if(!memberList.isEmpty()){  //동일한 멤버가 있다면
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }

    //
    @Override
    public void findID() {

    }

    @Override
    public void findPW() {

    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public void quit(Long id) {
        memberRepository.findById(id);
    }


}
