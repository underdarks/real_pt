package health.real_pt.price.api.GymPrice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GymPriceListDto<T> {
    private int count;
    private T data;
}
