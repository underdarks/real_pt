package health.real_pt.review.api.ptReview;

import health.real_pt.common.response.CommonResMessage;
import health.real_pt.common.response.CommonResEntity;
import health.real_pt.common.response.StatusCode;
import health.real_pt.review.dto.ptReview.PtReviewReqDto;
import health.real_pt.review.dto.ptReview.PtReviewResDto;
import health.real_pt.review.dto.ptReview.PtReviewResListDto;
import health.real_pt.review.service.ptReview.PtReviewService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
     * 리뷰 등록
     * @param ptId      : Member ID
     * @param files     : 리뷰 사진(파일)
     * @param reqDto    : 리뷰 내용
     */
    @ApiOperation(value = "PT 리뷰 등록" , notes = "리뷰를 등록합니다.")
    @PostMapping("")
    public ResponseEntity<CommonResEntity> savePtReview(
            @RequestParam(value = "pt-id") Long ptId ,
            @RequestPart(value = "images") List<MultipartFile> files,
            @RequestPart(value = "reqData") PtReviewReqDto reqDto)
    {
        ptReviewService.saveReview(reqDto, ptId,files);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.CREATED, CommonResMessage.CREATED_PT_REVIEW_SUCCESS),
                HttpStatus.CREATED

        );
    }


    /**
     *  PT 리뷰 수정
     */
    @ApiOperation(value = "PT 리뷰 수정" , notes = "리뷰 내용 및 이미지를 수정합니다.")
    @PatchMapping("/{id}")
    public ResponseEntity<CommonResEntity> updatePtReview(
            @PathVariable(value = "id") Long id,
            @RequestPart(value = "images") List<MultipartFile> files,
            @RequestPart(value = "udpData") PtReviewReqDto udpDto){

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
    @GetMapping(value = "/{gym-id}/{pt-id}")
    public ResponseEntity<CommonResEntity> findPtReview(@PathVariable(value = "gym-id") Long gymId,@PathVariable(value = "pt-id") Long ptId){
        List<PtReviewResDto> reviewList = ptReviewService.findReview(gymId, ptId);

        PtReviewResListDto listDto = new PtReviewResListDto(reviewList.size(), reviewList);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.READ_PT_REVIEW_SUCCESS,listDto),
                HttpStatus.OK
        );
    }

    /**
     * pt 리뷰 삭제
     */
    @ApiOperation(value = "PT 리뷰 삭제" , notes = "리뷰를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResEntity> deletePtReview(@PathVariable(value = "id") Long id){
        ptReviewService.deleteReview(id);

        return new ResponseEntity(
                CommonResEntity.createResponse(StatusCode.OK, CommonResMessage.DELETE_PT_REVIEW_SUCCESS),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "pt 리뷰 도움이 돼요(좋아요) 수정", notes = "리뷰의 도움이 돼요 수를 증가 or 감소합니다.")
    @PatchMapping("/good/{id}")
    public ResponseEntity<CommonResEntity> updateGood(@PathVariable(value = "id") Long id, @RequestBody String tag){
        Long count=0L;

        if(tag.equals("up")) {
            count = ptReviewService.addGood(id);
            return new ResponseEntity(
                    CommonResEntity.createResponse(StatusCode.OK,CommonResMessage.ADD_PT_REVIEW_GOOD_SUCCESS,count),
                    HttpStatus.OK
            );
        }

        else if(tag.equals("down")) {
            count = ptReviewService.subGood(id);
            return new ResponseEntity(
                    CommonResEntity.createResponse(StatusCode.OK,CommonResMessage.SUB_PT_REVIEW_GOOD_SUCCESS,count),
                    HttpStatus.OK
            );
        }

        return null;
    }


    @ApiOperation(value = "pt 리뷰 도움이 안돼요(싫어요) 수정", notes = "리뷰의 도움이 안돼요 수를 증가 or 감소합니다.")
    @PatchMapping("/bad/{id}")
    public ResponseEntity<CommonResEntity> updateBad(@PathVariable(value = "id") Long id, @RequestBody String tag){
        Long count=0L;

        if(tag.equals("up")) {
            count = ptReviewService.addBad(id);
            return new ResponseEntity(
                    CommonResEntity.createResponse(StatusCode.OK,CommonResMessage.ADD_PT_REVIEW_BAD_SUCCESS,count),
                    HttpStatus.OK
            );
        }

        else if(tag.equals("down")) {
            count = ptReviewService.subBad(id);
            return new ResponseEntity(
                    CommonResEntity.createResponse(StatusCode.OK,CommonResMessage.SUB_PT_REVIEW_BAD_SUCCESS,count),
                    HttpStatus.OK
            );
        }

        return null;
    }


}
