package health.real_pt.review.domain;

import health.real_pt.common.BaseTimeEntity;
import health.real_pt.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "PT_REVIEW")
public class PtReview extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PT_REVIEW_ID")
    private Long id;

    @ManyToOne  //PT_REVIEW가 FK를 가지게 됨(연관관계 주인)
    @JoinColumn(name = "MEMBER_ID")
    private Member pt;      //PT 트레이너

    @Column(name = "TOTAL")     //총점(별 5개중 별 몇개인지...)
    private Long total;

    @Column(name = "COMMENT")
    private String comment;     //리뷰 내용

    @Column(name = "GOOD")
    private Long good;          //도움이돼요 개수

    @Column(name = "BAD")
    private Long bad;           //도움 안되요 개수



}
