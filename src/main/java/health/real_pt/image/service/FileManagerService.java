package health.real_pt.image.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileManagerService {

    //다중 파일 업로드
    void uploadFiles(MultipartFile files)
}
