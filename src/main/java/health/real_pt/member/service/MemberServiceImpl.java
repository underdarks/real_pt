package health.real_pt.member.service;

import health.real_pt.exception.exception_handler.ExceptionType;
import health.real_pt.exception.exceptions.CommonApiExceptions;
import health.real_pt.gym.domain.Gym;
import health.real_pt.gym.service.GymService;
import health.real_pt.image.domain.MemberImage;
import health.real_pt.image.dto.ImageResDto;
import health.real_pt.image.service.MemberImageServiceImpl;
import health.real_pt.member.dto.LoginDto;
import health.real_pt.member.dto.MemberResDto;
import health.real_pt.member.dto.MemberReqDto;
import health.real_pt.member.domain.Member;
import health.real_pt.member.repository.MemberRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
@Transactional(readOnly = true)  //JPA는 트랜잭션 안에서 실행됨
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final GymService gymService;
    private final MemberImageServiceImpl memberImageService;

    public MemberServiceImpl(MemberRepository memberRepository, GymService gymService, MemberImageServiceImpl memberImageService) {
        this.memberRepository = memberRepository;
        this.gymService = gymService;
        this.memberImageService = memberImageService;
    }

    //로그인
    @Override
    public Member login(LoginDto loginDto) {
        Member member= memberRepository.findByUserId(loginDto.getUserId()).orElseThrow(() -> new CommonApiExceptions(ExceptionType.LOGIN_FAILED,"사용자를 찾지 못하였습니다."));
        return member;
    }

    //회원 가입
    @Override
    @Transactional
    public Long join(MemberReqDto reqDto, Long gymId, List<MultipartFile> files) {
        Gym gym = gymService.findEntity(gymId);
        reqDto.setGym(gym);

        Member member = Member.toEntity(reqDto);

        validateDuplicateMember(member);
        Long saveId = memberRepository.save(member);

        //멤버 프로필 이미지 저장
        memberImageService.uploadFiles(files,member);

        return saveId;
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
    public List<MemberResDto> findAllMembers() {
        List<Member> memberList = memberRepository.findAll();

         return memberList.stream()
                .map(m -> new MemberResDto().entityToDto(m,getMemberImages(m.getImages())))
                .collect(Collectors.toList());
    }

    //멤버 이미지 -> ImageResDto로 변환
    private List<ImageResDto> getMemberImages(List<MemberImage> images){
        return images.stream()
                .map(image -> new ImageResDto(image.getOriginalFileName(),image.getDownloadUri()))
                .collect(Collectors.toList());
    }

    @Override
    public MemberResDto findMember(Long id) {
        Member member = findEntity(id);

        List<ImageResDto> imageList=getMemberImages(member.getImages());    //멤버 이미지

        return new MemberResDto().entityToDto(member,imageList);
    }

    @Transactional
    @Override
    public MemberResDto updateMember(Long id, Long gymId, MemberReqDto udpDto, List<MultipartFile> files) {
        Member member = findEntity(id);

        //더티 체킹(엔티티 수정)
        member.updateEntity(udpDto);

        //멤버 프로필 수정(삭제 후 다시 생성)
        for (MemberImage image : member.getImages())
            memberImageService.deleteFiles(image.getFilepath());

        //멤버와 연관된 이미지들 삭제
        member.deleteMemberImages();

        //이미지 새로 등록
        memberImageService.uploadFiles(files,member);

        List<ImageResDto> imageList=getMemberImages(member.getImages());    //멤버 이미지

        return new MemberResDto().entityToDto(member,imageList);
    }

    @Transactional
    @Override
    public void quit(Long id) {
        memberRepository.delete(findEntity(id));
    }

    /**
     * Entity를 찾아
     */
    @Override
    public Member findEntity(Long id) {
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
