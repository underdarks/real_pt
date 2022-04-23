package health.real_pt.common;

import lombok.Getter;

import javax.persistence.*;


@Getter
@MappedSuperclass
public abstract class BaseImageEntity extends BaseTimeEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IMAGE_ID")
    private Long id;

    @Column(name = "ORIGINAL_FILE_NAME")
    protected String originalFileName;    //원본 파일 이름

    @Column(name = "STORED_FILE_NAME")
    protected String storedFileName;      //서버에 저장된 파일 이름(파일 이름 중복 방지)

    @Column(name = "FILE_PATH")
    protected String filepath;            //서버에 저장된 파일 경로

    @Column(name = "download_uri")
    protected String downloadUri;         //파일 다운 경로(URI)

    @Column(name = "FILE_SIZE")
    protected Long size;                  //파일 크기


}
