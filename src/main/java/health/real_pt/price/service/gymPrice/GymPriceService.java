package health.real_pt.price.service.gymPrice;

import health.real_pt.common.BaseInterface;
import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.dto.gymPrice.GymPriceReqDto;
import health.real_pt.price.dto.gymPrice.GymPriceResDto;

import java.util.List;

public interface GymPriceService extends BaseInterface<GymPrice> {

    /**
     *              Notice
     *
     * 헬스장 가격(GYM_PRICE) Service
     * 구현체 => GymPriceServiceImpl.java
     */

    /**
     *  가격 저장
     */
    Long savePrice(GymPriceReqDto reqDto, Long gymId);

    /**
     *  가격 수정
     */
    GymPriceResDto updatePrice(GymPriceReqDto reqDto);


    /**
     *  가격 삭제
     */
    void deletePrice(Long id);


    /**
     *  가격 단일 조회
     */
    GymPriceResDto findOnePrice(Long id);

    /**
     *  가격 전체 조회
     * @return
     */
    List<GymPriceResDto> findAllPrice(Long gymId);



}
