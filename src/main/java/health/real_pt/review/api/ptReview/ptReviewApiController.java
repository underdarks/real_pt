package health.real_pt.review.api.ptReview;

import health.real_pt.common.response.CommonResMessage;
import health.real_pt.common.response.CommonResEntity;
import health.real_pt.common.response.StatusCode;
import health.real_pt.review.dto.ptReview.PtReviewReqDto;
import health.real_pt.review.dto.ptReview.PtReviewResDto;
import health.real_pt.review.service.ptReview.PtReviewService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * PT 리뷰(PtReview) API Controller
 * Nember - PtReview(1:N 관계)
 */
@RestController
@RequestMapping("api/v1/ptreview")
@RequiredArgsConstructor
public class ptReviewApiController {

    private final PtReviewService ptReviewService;


    /**
     * PT 리뷰 등록
     * @param ptId      : Memmber_ID
     * @param reqDto
     *
     * 해당 PT의 리뷰 등록록     */
    @ApiOperation(value = "PT 리뷰 등록" , notes = "리뷰를 등록합니다.")
    @PostMapping("")
    public ResponseEntity<CommonResEntity> savePtReview(@RequestHeader(value = "pt-id") Long ptId , @RequestBody @Valid PtReviewReqDto reqDto){
        Long saveId = ptReviewService.saveReview(reqDto, ptId);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.CREATED, CommonResMessage.CREATED_PT_REVIEW_SUCCESS),
                HttpStatus.CREATED

        );
    }


    /**
     *  PT 리뷰 수정
     */
    @ApiOperation(value = "PT 리뷰 수정" , notes = "리뷰를 수정합니다.")
    @PatchMapping("/{id}")
    public ResponseEntity<CommonResEntity> updatePtReview(@PathVariable(value = "id") Long id,@RequestBody @Valid PtReviewReqDto udpDto){
        udpDto.setId(id);
        PtReviewResDto resDto = ptReviewService.updateReview(udpDto);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.UPDATE_PT_REVIEW_SUCCESS,resDto),
                HttpStatus.OK
        );
    }

    /**
     * pt 리뷰 조회
     * @param gymId  : Gym_ID
     * @param ptId   : Member_ID
     *
     * 특정 헬스장의 특정 트레이너의 리뷰(N) 조회
     */
    @ApiOperation(value = "PT 리뷰 조회" , notes = "리뷰를 조회합니다.")
    @GetMapping("/{gym-id}/{pt-id}")
    public ResponseEntity<CommonResEntity> findPtReview(@PathVariable(value = "gym-id") Long gymId,@PathVariable(value = "pt-id") Long ptId){
        List<PtReviewResDto> reviewList = ptReviewService.findReview(gymId, ptId);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.READ_PT_REVIEW_SUCCESS,reviewList),
                HttpStatus.OK
        );
    }

    /**
     * pt 리뷰 삭제
     */
    @ApiOperation(value = "PT 리뷰 삭제" , notes = "리뷰를 삭제합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResEntity> deletePtReview(@PathVariable(value = "id") Long id){
        ptReviewService.deleteReview(id);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.READ_PT_REVIEW_SUCCESS),
                HttpStatus.OK
        );
    }




}
