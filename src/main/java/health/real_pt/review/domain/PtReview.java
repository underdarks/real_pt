package health.real_pt.review.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.image.domain.PtReviewFile;
import health.real_pt.member.domain.Member;
import health.real_pt.review.dto.ptReview.PtReviewReqDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@Entity @Table(name = "PT_REVIEW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
@EntityListeners({AuditingEntityListener.class})
public class PtReview implements BaseEntity<PtReviewReqDto> {

    @PrePersist
    

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PT_REVIEW_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)  //PT_REVIEW가 FK를 가지게 됨(연관관계 주인)
    @JoinColumn(name = "PT_MEMBER_ID")
    private Member pt;          //PT(Personal Trainer)

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "WRITER_MEMBER_ID")
    private Member writer;      //작성 회원

    @Column(name = "TOTAL")     //총점(별 5개중 별 몇개인지...)
    private Long total;

    @Lob
    @Column(name = "COMMENT")
    private String comment;     //리뷰 내용

    @Column(name = "GOOD")
    private Long good;          //도움이돼요 개수

    @Column(name = "BAD")
    private Long bad;           //도움 안되요 개수

    @OneToMany(mappedBy = "ptReview", cascade = CascadeType.REMOVE, orphanRemoval = true)     //리뷰 삭제시 업로드 파일도 같이 삭제
    private List<PtReviewFile> uploadFiles =new ArrayList<>();


    @CreatedDate  //Insert 쿼리 발생 시, 현재 시간을 값으로 채워서 쿼리를 생성 후 insert
    @Column(name = "REG_DATE", updatable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime regDate; //등록시간

    @LastModifiedDate    //Update 쿼리 발생 시, 현재 시간을 값으로 채워서 쿼리를 생성 후 업데이트
    @Column(name = "MOD_DATE")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime modDate; //수정시간


//    @PrePersist
//    public void prePersist(){
//        this.good = (this.good == null ? 0: this.good);
//
//    }

    /**
     *  setter 대신 도메인 필드 변경하는 메서드들(setter 사용 지양)
     */

    public void changePt(Member pt){
        this.pt=pt;
    }

    public void changeWriter(Member writer){
        this.writer=writer;
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

