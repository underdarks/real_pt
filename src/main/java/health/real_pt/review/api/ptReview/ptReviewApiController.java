package health.real_pt.review.api.ptReview;

import health.real_pt.review.service.ptReview.PtReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ptreview")
@RequiredArgsConstructor
public class ptReviewApiController {

    private final PtReviewService ptReviewService;

    
}
