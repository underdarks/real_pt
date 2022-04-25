package health.real_pt.review.dto.ptReview;

import health.real_pt.member.domain.Member;
import lombok.Data;

import javax.persistence.*;

@Data
public class PtReviewReqDto {

    private Long id;
    private Member pt;          //PT 트레이너
    private Long total;         //별점
    private String comment;     //리뷰 내용
    private Long good;          //도움이돼요
    private Long bad;           //도움 안되요

}
