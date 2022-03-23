package health.real_pt.member.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResultDto<T> {
    private int count;
    private T data;
}
