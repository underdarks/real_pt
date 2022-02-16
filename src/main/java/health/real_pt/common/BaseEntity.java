package health.real_pt.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @Column(name = "REG_DATE")
    private String regDate; //등록시간

    @Column(name = "MOD_DATE")
    private String modDate; //수정시간

    @Column(name = "REG_MEMBER_ID")
    private String reg_member; //등록자

    @Column(name = "MOD_MEMBER_ID")
    private String mod_member; //수정자

}
