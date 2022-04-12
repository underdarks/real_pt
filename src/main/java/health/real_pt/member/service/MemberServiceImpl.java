package health.real_pt.member.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.service.GymService;
import health.real_pt.member.dto.MemberResDto;
import health.real_pt.member.domain.Member;
import health.real_pt.member.dto.MemberReqDto;
import health.real_pt.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
@Transactional(readOnly = true)  //JPA는 트랜잭션 안에서 실행됨
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final GymService gymService;

    public MemberServiceImpl(MemberRepository memberRepository, GymService gymService) {
        this.memberRepository = memberRepository;
        this.gymService = gymService;
    }

    @Override
    @Transactional
    public Long join(MemberReqDto reqDto, Long gymId) {
        Gym gym = gymService.findOne(gymId);
        reqDto.setGym(gym);

        Member member = Member.toEntity(reqDto);

        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    /**
     * 중복 회원 검증(Id로 찾기)
     */
    private void validateDuplicateMember(Member member) {
        List<Member> memberList = memberRepository.findByNameAndEmail(member);

        if (!memberList.isEmpty()) {  //동일한 멤버가 있다면
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
    public MemberResDto findMember(Long id) {
        Member member = findEntity(id);
        return new MemberResDto().entityToDto(member);
    }

    @Transactional
    @Override
    public MemberResDto updateMember(Long id, MemberReqDto memberReqDto) {
        Member member = findEntity(id);
        //더티 체킹(엔티티 수정)
        member.updateEntity(memberReqDto);

        return new MemberResDto().entityToDto(member);
    }


    @Transactional
    @Override
    public void quit(Long id) {
        memberRepository.delete(findEntity(id));
    }

    @Transactional
    @Override
    public Member findEntity(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException("member"));
    }

    @Override
    public void findID() {

    }

    @Override
    public void findPW() {

    }
}
