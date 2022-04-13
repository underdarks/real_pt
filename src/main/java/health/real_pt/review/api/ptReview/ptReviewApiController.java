package health.real_pt.review.api.ptReview;

import health.real_pt.common.response.CommonResponse;
import health.real_pt.review.service.ptReview.PtReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ptreview")
@RequiredArgsConstructor
public class ptReviewApiController {

    private final PtReviewService ptReviewService;


    @PostMapping("")
    public ResponseEntity<CommonResponse> savePtReview()


}
