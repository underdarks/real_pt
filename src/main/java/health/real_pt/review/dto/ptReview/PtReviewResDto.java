package health.real_pt.review.dto.ptReview;

import health.real_pt.common.BaseDto;
import health.real_pt.image.dto.PtReviewFileResDto;
import health.real_pt.review.domain.PtReview;
import lombok.Data;

import java.util.List;

@Data
public class PtReviewResDto {

    private String ptName;      //PT 이름
    private String gymName;     //소속 헬스장 이름
    private Long total;         //총 점수
    private String comment;     //리뷰 내용
    private Long good;          //도움이돼요 개수
    private Long bad;           //도움 안되요 개수
    private List<PtReviewFileResDto> reviewImages;   //리뷰 이미지


    //entity -> dto
    public PtReviewResDto entityToDto(PtReview ptReview,List<PtReviewFileResDto> reviewImages) {
        PtReviewResDto resDto = new PtReviewResDto();

        resDto.setPtName(ptReview.getPt().getName());
        resDto.setGymName(ptReview.getPt().getGym().getName());
        resDto.setTotal(ptReview.getTotal());
        resDto.setComment(ptReview.getComment());
        resDto.setGood(ptReview.getGood());
        resDto.setBad(ptReview.getBad());
        resDto.setReviewImages(reviewImages);

        return resDto;
    }
}
