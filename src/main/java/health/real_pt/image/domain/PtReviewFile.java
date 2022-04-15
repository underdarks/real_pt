package health.real_pt.image.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.image.dto.PtReviewFileReqDto;
import health.real_pt.review.domain.PtReview;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Table(name = "PTREVIEW_IMAGE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
public class PtReviewFile extends BaseTimeEntity implements BaseEntity<PtReviewFileReqDto> {

    //서버 파일 경로
    public static final String serverFilePath="D:/upload_image/pt_review/";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IMAGE_ID")
    private Long id;

    @Column(name = "ORIGINAL_FILE_NAME")
    private String originalFileName;    //원본 파일 이름

    @Column(name = "STORED_FILE_NAME")
    private String storedFileName;      //서버에 저장된 파일 이름(파일 이름 중복 방지)

    @Column(name = "FILE_PATH")
    private String filepath;            //서버에 저장된 파일 경로

    @Column(name = "FILE_SIZE")
    private Long size;                  //파일 크기


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PT_REVIEW_ID")
    private PtReview ptReview;      //리뷰

    //------ 연관관계 편의 메서드

    public void changePtReview(PtReview ptReview) {
        this.ptReview = ptReview;
        this.ptReview.getUploadFiles().add(this);
    }
    @Builder
    public PtReviewFile(String originalFileName, String storedFileName, String filepath, Long size, PtReview ptReview) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.filepath = filepath;
        this.size = size;

        changePtReview(ptReview);
    }

    @Override
    public void updateEntity(PtReviewFileReqDto ptReviewFileReqDto) {

    }
}
