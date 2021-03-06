package health.real_pt.review.dto.ptReview;

import health.real_pt.image.dto.ImageResDto;
import health.real_pt.review.domain.PtReview;
import lombok.Data;

import java.util.List;

@Data
public class PtReviewResDto {

    private Long id;
    private String ptName;      //PT 이름
    private String gymName;     //소속 헬스장 이름
    private Long total;         //총 점수
    private String comment;     //리뷰 내용
    private Long good;          //도움이돼요 개수
    private Long bad;           //도움 안되요 개수
    private List<ImageResDto> reviewImages;   //리뷰 이미지


    //entity -> dto
    public PtReviewResDto entityToDto(PtReview ptReview,List<ImageResDto> reviewImages) {
        PtReviewResDto resDto = new PtReviewResDto();

        resDto.setId(ptReview.getId());
        resDto.setPtName(ptReview.getPt().getName());
        resDto.setGymName(ptReview.getPt().getGym() == null ? "소속된 헬스장이 없습니다":ptReview.getPt().getGym() .getName());
        resDto.setTotal(ptReview.getTotal());
        resDto.setComment(ptReview.getComment());
        resDto.setGood(ptReview.getGood());
        resDto.setBad(ptReview.getBad());
        resDto.setReviewImages(reviewImages);

        return resDto;
    }
}
