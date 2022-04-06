package health.real_pt.price.dto.ptPrice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PtPriceListDto<T> {
    private T data;
    private int size;
}
