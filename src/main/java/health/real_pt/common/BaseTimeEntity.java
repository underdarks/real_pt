package health.real_pt.common;

import health.real_pt.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Clob;
import java.time.LocalDateTime;

import static org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.*;

@MappedSuperclass
@Getter
@EntityListeners({AuditingEntityListener.class})    //BaseTimeEntity에 Auditing(이벤트) 기능을 포함
public abstract class BaseTimeEntity {

    @CreatedDate  //Insert 쿼리 발생 시, 현재 시간을 값으로 채워서 쿼리를 생성 후 insert
    @Column(name = "REG_DATE", updatable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime regDate; //등록시간

    @LastModifiedDate    //Update 쿼리 발생 시, 현재 시간을 값으로 채워서 쿼리를 생성 후 업데이트
    @Column(name = "MOD_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime modDate; //수정시간

    @LastModifiedBy
    @Column(name = "REG_MEMBER_ID", updatable = false)
    private String reg_member; //등록자
    @UpdateTimestamp
    @CreatedBy
    @Column(name = "MOD_MEMBER_ID")
    private String mod_member; //수정자

    @Lob
    @Column(name = "REMARKS")
    private String  remarks;       //비고

}
