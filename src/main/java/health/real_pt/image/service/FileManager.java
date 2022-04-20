package health.real_pt.image.service;

import health.real_pt.common.exception_handler.ExceptionType;
import health.real_pt.common.exceptions.CommonApiExceptions;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 파일 연산하는 클래스
 */
public class FileManager {

    //업로드된 이미지 찾기
    public Resource getFiles(Path path, String fileName) {
        try {
            Path filePath = path.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists())
                return resource;

            else
                throw new CommonApiExceptions(ExceptionType.FILE_DOWNLOAD_EXCEPTION, "파일을 찾을 수 없습니다.");
        }
        catch (MalformedURLException e) {
            throw new CommonApiExceptions(ExceptionType.FILE_DOWNLOAD_EXCEPTION, "파일을 찾을 수 없습니다.");
        }
    }

    //파일 삭제
    public boolean deleteFiles(String filePath) {
        Path path = Paths.get(filePath);

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
