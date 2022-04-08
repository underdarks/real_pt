package health.real_pt.review.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.member.domain.Member;
import health.real_pt.review.dto.ptReview.PtReviewReqDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity @Table(name = "PT_REVIEW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class PtReview extends BaseTimeEntity implements BaseEntity<PtReviewReqDto> {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PT_REVIEW_ID")
    private Long id;

    @ManyToOne  //PT_REVIEW가 FK를 가지게 됨(연관관계 주인)
    @JoinColumn(name = "MEMBER_ID")
    private Member pt;          //PT(Personal Trainer)

    @Column(name = "TOTAL")     //총점(별 5개중 별 몇개인지...)
    private Long total;

    @Lob
    @Column(name = "COMMENT")
    private String comment;     //리뷰 내용

    @Column(name = "GOOD")
    private Long good;          //도움이돼요 개수

    @Column(name = "BAD")
    private Long bad;           //도움 안되요 개수

    /**
     *  setter 대신 도메인 필드 변경하는 메서드들(setter 사용 지양)
     */

    public void changePt(Member pt){
        this.pt=pt;
    }

    public void changeTotal(Long total){
        this.total=total;
    }

    public void changeComment(String comment){
        this.comment=comment;
    }

    public void addGood(){
        this.good++;
    }

    public void subGood(){
        this.good--;
    }

    public void addBad(){
        this.bad++;
    }

    public void subBad(){
        this.bad--;
    }

    //객체 생성(빌더 패턴)
    @Builder
    public PtReview(Member pt, Long total, String comment) {
        this.pt = pt;
        this.total = total;
        this.comment = comment;
    }

    //Dto -> Entity로 변환(객체 생성)
    public static PtReview toEntity(PtReviewReqDto reqDto){
        return PtReview.builder()
                .pt(reqDto.getPt())
                .total(reqDto.getTotal())
                .comment(reqDto.getComment())
                .build();
    }

    //엔티티 수정(더티 체킹)
    @Override
    public void updateEntity(PtReviewReqDto ptReviewReqDto) {
        if(ptReviewReqDto.getPt() != null)
            changePt(ptReviewReqDto.getPt());

        if(ptReviewReqDto.getComment() != null)
            changeComment(ptReviewReqDto.getComment());

        if(ptReviewReqDto.getTotal() != null)
            changeTotal(ptReviewReqDto.getTotal());

    }
}

