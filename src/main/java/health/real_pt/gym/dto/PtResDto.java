package health.real_pt.gym.dto;

import health.real_pt.image.dto.ImageResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * PT 정보 DTO
 */
@Data
public class PtResDto {


    private String representImageUrl;    //대표 이미지
    private String name;                 //PT 이름
    private String gymName;              //소속 헬스장 이름
    private String location;             //헬스장 위치

    @Builder
    public PtResDto(String representImageUrl, String name, String gymName, String location) {
        this.representImageUrl = representImageUrl;
        this.name = name;
        this.gymName = gymName;
        this.location = location;
    }
}
