package health.real_pt.price.service.ptPrice;

import health.real_pt.price.dto.ptPrice.PtPriceReqDto;
import health.real_pt.price.dto.ptPrice.PtPriceResDto;

import java.util.List;

public interface PtPriceService {
    /**
     *              Notice
     *
     * Pt 가격(PT_PRICE) Service
     * 구현체 => PtPriceServiceImpl.java
     */

    /**
     *  가격 저장
     */
    Long savePrice(PtPriceReqDto ptPriceReqDto, Long memberId);

    /**
     *  가격 수정
     */
    PtPriceResDto updatePrice(PtPriceReqDto updDto);


    /**
     *  가격 삭제
     */
    void deletePrice(Long Id);


    /**
     *  가격 단일 조회
     * @return
     */
    PtPriceResDto findOnePrice(Long ptPriceId);

    /**
     *  가격 전체 조회
     */
    List<PtPriceResDto> findAllPrice(Long gymId,Long ptId);

}
