package health.real_pt.gym.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GymResultDto <T>{
    private int count;
    private T data;

}
