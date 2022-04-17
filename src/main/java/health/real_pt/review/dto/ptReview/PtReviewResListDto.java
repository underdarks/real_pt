package health.real_pt.review.dto.ptReview;

import health.real_pt.common.BaseDto;
import health.real_pt.review.domain.PtReview;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PtReviewResListDto<T> {

    private int count;
    private T data;
}
