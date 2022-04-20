package health.real_pt.image.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class ImageResDto {

    private String fileName;       //원본 파일 이름
    private String downloadUri;    //다운로드 파일 경로


    public ImageResDto(String fileName, String downloadUri) {
        this.fileName = fileName;
        this.downloadUri = downloadUri;
    }


}
