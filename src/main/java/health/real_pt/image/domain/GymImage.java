package health.real_pt.image.domain;


import health.real_pt.common.BaseImageEntity;
import health.real_pt.gym.domain.Gym;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;

@Entity @Table(name = "GYM_IMAGE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "gym")
public class GymImage extends BaseImageEntity {

    //서버 파일 경로
    public static final String serverFilePath = "D:/upload_image/gym/";

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "GYM_ID")
    private Gym gym;

    //------ 연관관계 편의 메서드 =========

    //멤버의 이미지 추가
    public void addGymImage(Gym gym) {
        this.gym = gym;
        this.gym.getImages().add(this);
    }

    //객체 생성(빌더 패턴)
    @Builder
    public GymImage(String originalFileName, String storedFileName, String filepath, Long size, String downloadUri, Gym gym) {
        this.originalFileName = originalFileName;
        this.downloadUri = downloadUri;
        this.storedFileName = storedFileName;
        this.filepath = filepath;
        this.size = size;

        addGymImage(gym);
    }

}
