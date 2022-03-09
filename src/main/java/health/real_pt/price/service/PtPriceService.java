package health.real_pt.price.service;

import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.domain.PtPrice;
import health.real_pt.price.dto.PtPriceDto;

import java.util.List;
import java.util.Optional;

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
    Long savePtPrice(PtPriceDto ptPriceDto, Long memberId);

    /**
     *  가격 수정
     */
    void updatePtPrice(PtPriceDto ptPriceDto);


    /**
     *  가격 삭제
     */
    void deletePtPrice(PtPriceDto ptPriceDto);


    /**
     *  가격 단일 조회
     */
    Optional<PtPrice> findOnePrice(Long ptPriceId);

    /**
     *  가격 전체 조회
     */
    List<PtPrice> findAllPrice();

}
