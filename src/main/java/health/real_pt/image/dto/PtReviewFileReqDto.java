package health.real_pt.image.dto;

import health.real_pt.review.domain.PtReview;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Data
public class PtReviewFileReqDto {
    private Long id;
    private String fileName;
    private String filepath;        //서버에 저장된 파일 경로
    private String fileFullName;    //파일 풀네임임
    private PtReview ptReview;      //리뷰
}
