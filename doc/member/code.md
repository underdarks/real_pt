## 회원과 관련된 주요 엔티티들만 모아놓은 문서입니다.
자세한 코드를 보고 싶으시면 repo Clone 하셔서 보시면됩니다.



### Member 엔티티

회원은 Gym과, MemberImage, ptReview, ptPrice와 1:N(@OnetoMany) 관계를 가진다.

~~~java
package health.real_pt.member.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.domain.Gym;
import health.real_pt.image.domain.MemberImage;
import health.real_pt.member.dto.MemberReqDto;
import health.real_pt.price.domain.PtPrice;
import health.real_pt.review.domain.PtReview;
import health.real_pt.security.encryption.SHA256;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
public class Member extends BaseTimeEntity implements BaseEntity<MemberReqDto>, UserDetails {   //SpringSecurity는 UserDetails 객체를 통해 권한 정보 관리하기 때문에 UserDetails 상속 후 재정의 해야한다
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @NotBlank(message = "ID는 필수 값입니다!")
    @Column(name = "USER_ID", unique = true)
    private String userId;

    @NotBlank(message = "비밀번호는 필수 값입니다!")
    @Column(name = "PASSWORD")
    private String password;

    @NotBlank(message = "이름은 필수 값입니다!")
    @Column(name = "NAME")
    private String name;

    @NotBlank(message = "이메일은 필수 값입니다!")
    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotBlank(message = "전화번호는 필수 값입니다!")
    @Column(name = "PHONE")
    private String phone;

    @Column(name = "BIRTHDAY")
    private LocalDate birthDay;

    @NotBlank(message = "닉네임은 필수 값입니다!")
    @Column(name = "NICKNAME", unique = true)
    private String nickname;


    //Enum타입은 꼭 String으로 써라 Ordinal은 2가지 값만 갖는다. 따라서 확장 안됨
    @NotNull(message = "회원 타입은 필수 값입니다!")
    @Enumerated(EnumType.STRING)
    private MemberType memberType;      //회원 타입

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "GYM_ID")
    private Gym gym;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST, orphanRemoval = true) //멤버 삭제시 사진 같이 삭제
    private List<MemberImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "pt", orphanRemoval = true)     //PT 삭제시 리뷰 같이 삭제
    private List<PtReview> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "pt",cascade = CascadeType.PERSIST, orphanRemoval = true)     //PT 삭제시 가격 같이 삭제
    private List<PtPrice> prices=new ArrayList<>();

    @ElementCollection(fetch = EAGER)
//    @Builder.Default
    private List<String> roles=new ArrayList<>();

    //=============== UserDetails 상속 ====================
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return userId;  //Spring Security에서 사용하는 userName을 userId로 설정
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * setter 대신 도메인 객체 변경하는 메서드들(setter 사용 지양)
     */

    public void changePW(String password) {
        this.password = password;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    //============= 연관관계 편의 메서드 =========================

    //헬스장 - 멤버 연결
    public void addGym(Gym gym) {
        this.gym = gym;
        this.gym.getPt().add(this);
    }

    //Pt가 속한 헬스장 연관관계 해제
   public void deleteGym(){
        this.gym=null;
    }

    //멤버 - 이미지 삭제(고아 객체 자동 삭제)
    public void deleteMemberImages() {
        int size = images.size();

        for (int i = 0; i < size; i++)
            this.images.remove(0);
    }

    /* ============================================================================================================== */

    //객체 생성 빌더 패턴
    @Builder
    public Member(String userId, String password,
                  String name, String email,
                  String phone, LocalDate birthDay,
                  String nickname, MemberType memberType,
                  Gym gym,List<String> roles) {

        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDay = birthDay;
        this.nickname = nickname;
        this.memberType = memberType;
        this.roles=roles;
        addGym(gym);
    }

    //DTO -> Entity로 변환
    public static Member toEntity(MemberReqDto memberReqDto) {
        return Member.builder()
                .userId(memberReqDto.getUserId())
                .password(SHA256.getSHA256HashCode(memberReqDto.getPassword()))    //비밀번호 SHA256 암호화
                .name(memberReqDto.getName())
                .email(memberReqDto.getEmail())
                .phone(memberReqDto.getPhone())
                .birthDay(memberReqDto.getBirthDay())
                .nickname(memberReqDto.getNickname())
                .memberType(memberReqDto.getMemberType())
                .roles(Collections.singletonList("ROLE_USER"))   //최초 가입시 USER 권한(꼭, DB 저장시 ROLE_이 붙어야함
                .gym(memberReqDto.getGym())
                .build();

    }

    @Override
    public void updateEntity(MemberReqDto memberReqDto) {
        if (memberReqDto.getGym() != null)
            addGym(memberReqDto.getGym());

        if (memberReqDto.getEmail() != null)
            changeEmail(memberReqDto.getEmail());

        if (memberReqDto.getNickname() != null)
            changeNickname(memberReqDto.getNickname());

        if (memberReqDto.getPassword() != null)
            changePW(SHA256.getSHA256HashCode(memberReqDto.getPassword()));

        if (memberReqDto.getPhone() != null)
            changePhone(memberReqDto.getPhone());
    }


}

~~~
<br>

- - -


### MemberImage 엔티티

~~~java
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



~~~
<br>

- - -


### Gym 엔티티

~~~java
package health.real_pt.gym.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.gym.dto.GymReqDto;
import health.real_pt.image.domain.GymImage;
import health.real_pt.member.domain.Member;
import health.real_pt.price.domain.GymPrice;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "GYM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = {"pt","images","prices"})
public class Gym extends BaseTimeEntity implements BaseEntity<GymReqDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GYM_ID")
    private Long id;

    @NotBlank(message = "헬스장 이름은 필수 값 입니다.")
    @Column(name = "NAME", unique = true)
    private String name;    //헬스장 이름(중복 불가, 대신 체인점은 끝에 xx점 붙이기 ex. 스포애니 - 신림사거리 1호점)

    @Lob
    @Column(name = "INFO")
    private String info;      //헬스장 정보

    @Lob
    @Column(name = "OPEN_TIME")
    private String openTime;    //영업 시간

    @Lob
    @Column(name = "PROGRAM")
    private String program;      //운영 프로그램

    @NotBlank
    @Column(name = "LOCATION")
    private String location;    //위치 주소

    @Lob
    @Column(name = "EXTRA_SERVICE")
    private String extraService;  //부가서비스

    @Lob
    @Column(name = "FACILITIES")
    private String facilites;     //편의시설

    //Enum타입은 꼭 String으로 써라 Ordinal은 2가지 값만 갖는다. 따라서 확장 안됨
    @Enumerated(EnumType.STRING)
    private GymStatus gymStatus;    //헬스장 영업 상태


    @OneToMany(mappedBy = "gym")     //헬스장 PT 리스트
    private List<Member> pt = new ArrayList<>();

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)     //헬스장 이미지
    private List<GymImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)     //헬스장 가격
    private List<GymPrice> prices = new ArrayList<>();


    //============= 연관관계 편의 메서드 =========================


    //헬스장 - 이미지 삭제(고아 객체 자동 삭제)
    public void deleteGymImages() {
        int size = images.size();

        for (int i = 0; i < size; i++)
            this.images.remove(0);
    }

    //헬스장 - PT 삭제
    public void deleteGym(){
        int size=getPt().size();

        for (int i = 0; i < size; i++) {
             getPt().get(0).deleteGym();
             getPt().remove(0);
        }
    }




    /**
     * setter 대신 도메인 객체 변경하는 메서드들(setter 사용 지양)
     */

    public void changeName(String name) {
        if (name != null && !name.isEmpty())
            this.name = name;
    }

    public void changeInfo(String info) {
        if (info != null && !info.isEmpty())
            this.info = info;
    }

    public void changeOpenTime(String openTime) {
        if (openTime != null && !openTime.isEmpty())
            this.openTime = openTime;
    }

    public void changeProgram(String program) {
        if (program != null && !program.isEmpty())
            this.program = program;
    }

    public void changeLocation(String location) {
        if (location != null && !location.isEmpty())
            this.location = location;
    }

    public void changeExtraService(String extraService) {
        if (extraService != null && !extraService.isEmpty())
            this.extraService = extraService;
    }

    public void changeFacilites(String facilites) {
        if (facilites != null && !facilites.isEmpty())
            this.facilites = facilites;
    }

    /* ============================================================================================================== */

    @Builder
    public Gym(String name, String info, String openTime, String program, String location, String extraService, String facilites, GymStatus gymStatus) {
        this.name = name;
        this.info = info;
        this.openTime = openTime;
        this.program = program;
        this.location = location;
        this.extraService = extraService;
        this.facilites = facilites;
        this.gymStatus = gymStatus;
    }

    //Dto -> Entity로 변환(객체 생성)
    public static Gym toEntity(GymReqDto gymReqDto) {
        return Gym.builder()
                .name(gymReqDto.getName())
                .info(gymReqDto.getInfo())
                .openTime(gymReqDto.getOpenTime())
                .program(gymReqDto.getProgram())
                .location(gymReqDto.getLocation())
                .extraService(gymReqDto.getExtraService())
                .facilites(gymReqDto.getFacilites())
                .build();

    }

    //Entity 수정을 위한 공통 메서드
    @Override
    public void updateEntity(GymReqDto gymReqDto) {
        changeName(gymReqDto.getName());
        changeInfo(gymReqDto.getInfo());
        changeOpenTime(gymReqDto.getOpenTime());
        changeProgram(gymReqDto.getProgram());
        changeLocation(gymReqDto.getLocation());
        changeExtraService(gymReqDto.getExtraService());
        changeFacilites(gymReqDto.getFacilites());

    }

}

~~~
<br>

- - -


### PtReview 

~~~java
package health.real_pt.review.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.gym.domain.Gym;
import health.real_pt.image.domain.PtReviewImage;
import health.real_pt.member.domain.Member;
import health.real_pt.review.dto.ptReview.PtReviewReqDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@Entity @Table(name = "PT_REVIEW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
@EntityListeners({AuditingEntityListener.class})
public class PtReview implements BaseEntity<PtReviewReqDto> {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PT_REVIEW_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)  //PT_REVIEW가 FK를 가지게 됨(연관관계 주인)
    @JoinColumn(name = "PT_MEMBER_ID")
    private Member pt;          //PT(Personal Trainer)

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "WRITER_MEMBER_ID")
    private Member writer;      //작성 회원

    @Column(name = "TOTAL")     //총점(별 5개중 별 몇개인지...)
    private Long total;

    @Lob
    @Column(name = "COMMENT")
    private String comment;     //리뷰 내용

    @Column(name = "GOOD")
    private Long good;          //도움이돼요 개수

    @Column(name = "BAD")
    private Long bad;           //도움 안되요 개수

    @OneToMany(mappedBy = "ptReview", cascade = CascadeType.ALL, orphanRemoval = true)     //리뷰 삭제시 업로드 파일도 같이 삭제(orpahnRemoval -> 고아 객체 삭제)
    private List<PtReviewImage> images =new ArrayList<>();


    @CreatedDate  //Insert 쿼리 발생 시, 현재 시간을 값으로 채워서 쿼리를 생성 후 insert
    @Column(name = "REG_DATE", updatable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime regDate; //등록시간

    @LastModifiedDate    //Update 쿼리 발생 시, 현재 시간을 값으로 채워서 쿼리를 생성 후 업데이트
    @Column(name = "MOD_DATE")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime modDate; //수정시간

    //===========================================================================================

    @PrePersist
    public void prePersist(){
        good = (good == null ? 0: good);
        bad = (bad == null ? 0: bad);
    }

    /**
     *  setter 대신 도메인 필드 변경하는 메서드들(setter 사용 지양)
     */

    public void changePt(Member pt){
        this.pt=pt;
    }

    public void changeWriter(Member writer){
        this.writer=writer;
    }

    public void changeTotal(Long total){
        this.total=total;
    }

    public void changeComment(String comment){
        this.comment=comment;
    }

    public void addGood(){
        this.good++;
    }

    public void subGood(){
        this.good--;
    }

    public void addBad(){
        this.bad++;
    }

    public void subBad(){
        this.bad--;
    }

    //객체 생성(빌더 패턴)
    @Builder
    public PtReview(Member pt, Long total, String comment) {
        this.pt = pt;
        this.total = total;
        this.comment = comment;
    }

    //Dto -> Entity로 변환(객체 생성)
    public static PtReview toEntity(PtReviewReqDto reqDto){
        return PtReview.builder()
                .pt(reqDto.getPt())
                .total(reqDto.getTotal())
                .comment(reqDto.getComment())
                .build();
    }

    //엔티티 수정(더티 체킹)
    @Override
    public void updateEntity(PtReviewReqDto ptReviewReqDto) {
        if(ptReviewReqDto.getPt() != null)
            changePt(ptReviewReqDto.getPt());

        if(ptReviewReqDto.getComment() != null)
            changeComment(ptReviewReqDto.getComment());

        if(ptReviewReqDto.getTotal() != null)
            changeTotal(ptReviewReqDto.getTotal());

    }
}


~~~

<br>

- - -


### PtPrice

~~~java
package health.real_pt.price.domain;

import health.real_pt.common.BaseEntity;
import health.real_pt.common.BaseTimeEntity;
import health.real_pt.member.domain.Member;
import health.real_pt.price.dto.ptPrice.PtPriceReqDto;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "PT_PRICE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터 없는 기본 생성자 생성, 접근 제한을 Protected로 설정하여 외부에서 객체 생성을 허용하지 않음
@ToString(exclude = "")
public class PtPrice extends BaseTimeEntity implements BaseEntity<PtPriceReqDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PT_PRICE_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)    //FK를 가지는 쪽이 Many, FetchType.Lazy로 설정하면 프록시 객체로 조회,GymPrice 엔티티만 DB에서 조회,
    @JoinColumn(name = "MEMBER_ID")     //Member PK
    private Member pt;          //Personal Trainer(개인 트레이너)

    @Column(name = "REGULAR_PRICE")
    private Long regularPrice;  //정상가

    @Column(name = "DISCOUNT_PRICE")
    private Long discountPrice; //할인가

    @Column(name = "TIMES")
    private String times;  //횟수 (ex. 10회, 20회, 30회 ... 혹은 OT 무료 x회)


    /**
     * setter 대신 도메인 객체 변경하는 메서드들(setter 사용 지양)
     */

    public void changeTrainer(Member trainer) {
        this.pt = trainer;
    }

    public void changeRegularPrice(Long regularPrice) {
        this.regularPrice = regularPrice;
    }

    public void changeDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void changeTimes(String times) {
        this.times = times;
    }


    // =========== 연관관계 편의 메서드

    private void addPtPrice(Member pt){
        this.pt=pt;
        this.pt.getPrices().add(this);
    }



    /* ============================================================================================================== */

    @Builder    //객체 생성(빌더 패턴)
    public PtPrice(Member pt, Long regularPrice, Long discountPrice, String times) {
        this.regularPrice = regularPrice;
        this.discountPrice = discountPrice;
        this.times = times;

        addPtPrice(pt);
    }

    public static PtPrice toEntity(PtPriceReqDto ptPriceReqDto) {
        return PtPrice.builder()
                .pt(ptPriceReqDto.getPt())
                .regularPrice(ptPriceReqDto.getRegularPrice())
                .discountPrice(ptPriceReqDto.getDiscountPrice())
                .times(ptPriceReqDto.getTimes())
                .build();
    }

    @Override
    public void updateEntity(PtPriceReqDto ptPriceReqDto) {
        if (ptPriceReqDto.getPt() != null)
            changeTrainer(ptPriceReqDto.getPt());

        if (ptPriceReqDto.getDiscountPrice() != null)
            changeDiscountPrice(ptPriceReqDto.getDiscountPrice());

        if (ptPriceReqDto.getRegularPrice() != null)
            changeRegularPrice(ptPriceReqDto.getRegularPrice());

        if (ptPriceReqDto.getTimes() != null)
            changeTimes(ptPriceReqDto.getTimes());
    }
}


~~~






