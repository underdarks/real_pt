package health.real_pt.member.service;

import health.real_pt.common.exception_handler.ExceptionType;
import health.real_pt.common.exceptions.CommonApiExceptions;
import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.service.GymService;
import health.real_pt.member.dto.MemberReqResDto;
import health.real_pt.member.dto.MemberResResDto;
import health.real_pt.member.domain.Member;
import health.real_pt.member.repository.MemberRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public Long join(MemberReqResDto reqDto, Long gymId) {
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
            throw new DuplicateKeyException("이미 존재하는 회원입니다!");
        }
    }

    @Override
    public List<MemberResResDto> findAllMembers() {
        List<Member> memberList = memberRepository.findAll();

        return memberList.stream()
                .map(m -> new MemberResResDto().entityToDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public MemberResResDto findMember(Long id) {
        Member member = findEntity(id);
        return new MemberResResDto().entityToDto(member);
    }

    @Transactional
    @Override
    public MemberResResDto updateMember(Long id, MemberReqResDto memberReqDto) {
        Member member = findEntity(id);
        //더티 체킹(엔티티 수정)
        member.updateEntity(memberReqDto);

        return new MemberResResDto().entityToDto(member);
    }


    @Transactional
    @Override
    public void quit(Long id) {
        memberRepository.delete(findEntity(id));
    }

    /**
     * ResDto로 Entity를 찾아
     */
    @Transactional
    private Member findEntity(Long id) {
        return memberRepository.findById(id).orElseThrow(() ->
                new CommonApiExceptions(ExceptionType.ENTITY_NOT_FOUND_EXCEPTION,"id = " + id + "인 Member 객체를 찾을 수 없습니다.")
        );
    }

    @Override
    public void findID() {

    }

    @Override
    public void findPW() {

    }
}
