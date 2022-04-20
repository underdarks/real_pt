package health.real_pt.image.domain;

import health.real_pt.common.BaseImageEntity;
import health.real_pt.review.domain.PtReview;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "PTREVIEW_IMAGE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
public class PtReviewImage extends BaseImageEntity {

    //서버 파일 경로
    public static final String serverFilePath = "D:/upload_image/pt_review/";

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PT_REVIEW_ID")
    private PtReview ptReview;      //리뷰

    //------ 연관관계 편의 메서드
    public void connectPtReviewWithImage(PtReview ptReview) {
        this.ptReview = ptReview;
        this.ptReview.getImages().add(this);
    }

    //객체 생성(빌더 패턴)
    @Builder
    public PtReviewImage(String originalFileName, String storedFileName, String filepath, Long size, String downloadUri, PtReview ptReview) {
        this.originalFileName = originalFileName;
        this.downloadUri = downloadUri;
        this.storedFileName = storedFileName;
        this.filepath = filepath;
        this.size = size;

        connectPtReviewWithImage(ptReview);
    }


}
