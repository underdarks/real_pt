package health.real_pt.member.service;

import health.real_pt.member.dto.MemberResDto;
import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberReqDto;
import health.real_pt.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
@Transactional(readOnly = true)  //JPA는 트랜잭션 안에서 실행됨
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Long join(MemberReqDto memberReqDto) {
        try {
            Member member = Member.toEntity(memberReqDto);

            validateDuplicateMember(member);
            return  memberRepository.save(member);
        }
        finally {

        }
    }

    /**
     * 중복 회원 검증(Id로 찾기)
     */
    private void validateDuplicateMember(Member member){
//        System.out.println("member = " + member.getId());
        List<Member> memberList = memberRepository.findByNameAndEmail(member);

        if(!memberList.isEmpty()){  //동일한 멤버가 있다면
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }

    @Override
    public List<MemberResDto> findAllMembers() {
        List<Member> memberList = memberRepository.findAll();

        return memberList.stream()
                .map(m -> new MemberResDto().entityToDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public MemberResDto findMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Member 객체를 찾을 수 없습니다."));
        return new MemberResDto().entityToDto(member);
    }

    @Transactional
    @Override
    public MemberResDto updateMember(Long memberId, MemberReqDto memberReqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Member 객체를 찾을 수 없습니다."));
        //더티 체킹(엔티티 수정)
        member.updateEntity(memberReqDto);

        return new MemberResDto().entityToDto(member);
    }

    @Transactional
    @Override
    public void quit(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);

        Member deleteMember = memberOptional.orElseThrow(() -> new NoSuchElementException("meber 객체를 찾을 수 없습니다!"));
        memberRepository.delete(deleteMember);
    }

    @Override
    public void findID() {

    }

    @Override
    public void findPW() {

    }
}
