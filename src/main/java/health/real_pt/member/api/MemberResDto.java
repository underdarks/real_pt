package health.real_pt.member.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResDto<T> {
    private int count;
    private T data;
}
