package health.real_pt.image.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PtReviewFileResDto {

    private String fileName;       //원본 파일 이름
    private String downloadUri;    //다운로드 파일 경로

    @Builder
    public PtReviewFileResDto(String fileName, String downloadUri) {
        this.fileName = fileName;
        this.downloadUri = downloadUri;
    }


}
