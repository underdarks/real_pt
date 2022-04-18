package health.real_pt.review.service.ptReview;

import health.real_pt.common.exception_handler.ExceptionType;
import health.real_pt.common.exceptions.CommonApiExceptions;
import health.real_pt.image.domain.PtReviewImage;
import health.real_pt.image.dto.PtReviewImageResDto;
import health.real_pt.image.service.ImageService;
import health.real_pt.image.service.PtReviewImageServiceImpl;
import health.real_pt.member.domain.Member;
import health.real_pt.member.service.MemberService;
import health.real_pt.review.domain.PtReview;
import health.real_pt.review.dto.ptReview.PtReviewReqDto;
import health.real_pt.review.dto.ptReview.PtReviewResDto;
import health.real_pt.review.repository.ptReview.PtReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PtReviewServiceImpl implements PtReviewService{

    private final PtReviewRepository ptReviewRepository;
    private final MemberService memberService;
    private final PtReviewImageServiceImpl ptReviewImageService;

    @Transactional
    @Override
    public Long saveReview(PtReviewReqDto reqDto, Long ptId, List<MultipartFile> files) {
        Member pt = memberService.findEntity(ptId);
        reqDto.setPt(pt);

        PtReview ptReview = PtReview.toEntity(reqDto);

        //리뷰 내용 저장
        Long saveId = ptReviewRepository.save(ptReview);

        //리뷰 사진(파일) 저장
        ptReviewImageService.uploadFiles(files,ptReview);

        return saveId;
    }

    @Transactional
    @Override
    public PtReviewResDto updateReview(PtReviewReqDto updDto) {
        PtReview ptReview = findEntity(updDto.getId());

        //엔티티 수정
        ptReview.updateEntity(updDto);

        List<PtReviewImageResDto> reviewImages = ptReview.getReviewImages().stream()
                .map(file -> new PtReviewImageResDto(file.getOriginalFileName(), file.getDownloadUri()))
                .collect(Collectors.toList());


        return new PtReviewResDto().entityToDto(ptReview,reviewImages);
    }

    @Override
    public List<PtReviewResDto> findReview(Long gymId, Long ptId) {
        //최근 작성일 기준 정렬(디폴트 값)
        List<PtReview> ptReviewList = ptReviewRepository.findAll(gymId, ptId, "regDate desc");
        List<PtReviewResDto> result=new ArrayList<>();

        for (PtReview ptReview : ptReviewList) {    //리뷰
            List<PtReviewImageResDto> reviewImageList=new ArrayList<>();     //리뷰에 등록된 이미지(N개)

            for (PtReviewImage file : ptReview.getReviewImages()) {
                reviewImageList.add(
                        PtReviewImageResDto.builder()
                        .fileName(file.getOriginalFileName())
                        .downloadUri(file.getDownloadUri())
                        .build()
                );

            }

            result.add(new PtReviewResDto().entityToDto(ptReview,reviewImageList));
        }

        return result;
    }

    @Override
    public List<PtReviewResDto> findAllSortedReviews(Long gymId, Long ptId, String sortType) {
        return null;
    }

    @Transactional
    @Override
    public void deleteReview(Long id) {
        PtReview ptReview = findEntity(id);
        ptReviewRepository.delete(ptReview);
    }

    @Override
    public PtReview findEntity(Long id) {
        return ptReviewRepository.findById(id).orElseThrow(() ->
                new CommonApiExceptions(ExceptionType.ENTITY_NOT_FOUND_EXCEPTION,"id = " + id + "인 PtReview 객체를 찾을 수 없습니다.")
        );
    }


    @Transactional
    @Override
    public Long addGood(Long id) {
        PtReview ptReview = findEntity(id);
        ptReview.addGood();

        return ptReview.getGood();
    }

    @Transactional
    @Override
    public Long subGood(Long id) {
        PtReview ptReview = findEntity(id);
        ptReview.subGood();

        return ptReview.getGood();
    }

    @Transactional
    @Override
    public Long addBad(Long id) {
        PtReview ptReview = findEntity(id);
        ptReview.addBad();

        return ptReview.getBad();
    }

    @Transactional
    @Override
    public Long subBad(Long id) {
        PtReview ptReview = findEntity(id);
        ptReview.subBad();

        return ptReview.getBad();
    }

}
