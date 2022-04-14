package health.real_pt.image.domain;

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
public class PtReviewFile {

    //서버 파일 경로
    public static final String serverFilePath="D:/upload_image/pt_review/";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IMAGE_ID")
    private Long id;

    @Column(name = "")
    private String fileName;

    @Column(name = "file_path")
    private String filepath;        //서버에 저장된 파일 경로

    @Column(name = "")
    private String fileFullName;    //파일 풀네임임

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PT_REVIEW_ID")
    private PtReview ptReview;      //리뷰


    @Builder
    public PtReviewFile(String fileName, String filepath, String fileFullName, PtReview ptReview) {
        this.fileName = fileName;
        this.filepath = filepath;
        this.fileFullName = fileFullName;
        this.ptReview = ptReview;
    }


}
