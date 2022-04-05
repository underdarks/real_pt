package health.real_pt.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberListDto<T> {
    private int count;
    private T data;
}
