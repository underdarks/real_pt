package health.real_pt.review.dto.ptReview;

import health.real_pt.common.BaseResDto;
import health.real_pt.review.domain.PtReview;
import lombok.Data;

@Data
public class PtReviewResResDto implements BaseResDto<PtReview, PtReviewResResDto> {

    private Long id;
    private String ptName;      //PT 이름
    private Long total;         //총 점수
    private String comment;     //리뷰 내용
    private Long good;          //도움이돼요 개수
    private Long bad;           //도움 안되요 개수

    //entity -> dto
    @Override
    public PtReviewResResDto entityToDto(PtReview ptReview) {
        PtReviewResResDto resDto = new PtReviewResResDto();

        resDto.setPtName(ptReview.getPt().getName());
        resDto.setTotal(ptReview.getTotal());
        resDto.setComment(ptReview.getComment());
        resDto.setGood(ptReview.getGood());
        resDto.setBad(ptReview.getBad());

        return resDto;
    }
}
