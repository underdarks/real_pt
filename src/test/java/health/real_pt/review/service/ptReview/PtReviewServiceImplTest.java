package health.real_pt.review.service.ptReview;

import health.real_pt.member.repository.MemberRepository;
import health.real_pt.review.dto.ptReview.PtReviewReqDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PtReviewServiceImplTest {

    @Autowired
    PtReviewServiceImpl ptReviewService;
    
    @Autowired
    MemberRepository memberRepository;
    

    public PtReviewReqDto createPtReview(){
        PtReviewReqDto reqDto=new PtReviewReqDto();
        reqDto.setComment("운동을 잘 알려줘요.");
        reqDto.setTotal(4L);

        return reqDto;
    }


    @Test
    public void PT_리뷰저장_성공(){
        //given
        PtReviewReqDto reqDto = createPtReview();

        //when
        Long saveId = ptReviewService.saveReview(reqDto, 2L);

        //then
        Assertions.assertThat(saveId).isNotNull();
    }
    
    @Test
    public void 영속성_트랜잭션_테스트(){
        //given
        PtReviewReqDto reqDto=new PtReviewReqDto();


        //when

        //then
    }


}