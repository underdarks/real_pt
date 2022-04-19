package health.real_pt.image.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ImageService<T> {

    //다중 파일 업로드
    void uploadFiles(List<MultipartFile> files, T entity);

    //파일 다운로드
    Resource getUploadedFiles(String fileName);

    boolean deleteFiles(String filePath);




}
