package health.real_pt.common;

import health.real_pt.member.domain.Member;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.sql.Clob;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseTimeEntity {

    @CreationTimestamp  //Insert 쿼리 발생 시, 현재 시간을 값으로 채워서 쿼리를 생성 후 insert
    @Column(name = "REG_DATE")
    private LocalDateTime regDate; //등록시간

    @UpdateTimestamp    //Update 쿼리 발생 시, 현재 시간을 값으로 채워서 쿼리를 생성 후 업데이트
    @Column(name = "MOD_DATE")
    private LocalDateTime modDate; //수정시간

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    @Column(name = "REG_MEMBER_ID")
    private Member reg_member; //등록자

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    @Column(name = "MOD_MEMBER_ID")
    private Member mod_member; //수정자

    @Column(name = "REMARKS")
    private Clob remarks;       //비고

}
