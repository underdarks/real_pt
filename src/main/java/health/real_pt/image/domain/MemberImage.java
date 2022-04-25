package health.real_pt.image.domain;


import health.real_pt.common.BaseImageEntity;
import health.real_pt.member.domain.Member;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Table(name = "MEMBER_IMAGE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class MemberImage extends BaseImageEntity {

    //서버 파일 경로
    public static final String serverFilePath = "D:/upload_image/member/";

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    //------ 연관관계 편의 메서드 =========

    //멤버의 이미지 추가
    public void addMemberImage(Member member) {
        this.member = member;
        this.member.getImages().add(this);
    }


    //객체 생성(빌더 패턴)
    @Builder
    public MemberImage(String originalFileName, String storedFileName, String filepath, Long size, String downloadUri, Member member) {
        this.originalFileName = originalFileName;
        this.downloadUri = downloadUri;
        this.storedFileName = storedFileName;
        this.filepath = filepath;
        this.size = size;

        addMemberImage(member);
    }

}
