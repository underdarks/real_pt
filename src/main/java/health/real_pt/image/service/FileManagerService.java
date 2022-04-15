package health.real_pt.image.service;

import health.real_pt.review.domain.PtReview;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileManagerService {

    //다중 파일 업로드
    void uploadFiles(List<MultipartFile> files, PtReview review);
}
