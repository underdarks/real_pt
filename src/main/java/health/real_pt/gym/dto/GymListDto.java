package health.real_pt.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GymListDto<T>{
    private int count;
    private T data;

}
