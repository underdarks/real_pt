package health.real_pt.review.dto.ptReview;

import health.real_pt.common.BaseDto;
import health.real_pt.member.domain.Member;
import health.real_pt.review.domain.PtReview;
import lombok.Data;

@Data
public class PtReviewResDto implements BaseDto<PtReview,PtReviewResDto> {

    private Long id;
    private String ptName;      //PT 이름
    private Long total;         //총 점수
    private String comment;     //리뷰 내용
    private Long good;          //도움이돼요 개수
    private Long bad;           //도움 안되요 개수

    @Override
    public PtReviewResDto entityToDto(PtReview ptReview) {
        PtReviewResDto resDto = new PtReviewResDto();

        resDto.setPtName(ptReview.getPt().getName());
        resDto.setTotal(ptReview.getTotal());
        resDto.setComment(ptReview.getComment());
        resDto.setGood(ptReview.getGood());
        resDto.setBad(ptReview.getBad());

        return resDto;
    }
}
