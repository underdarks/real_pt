package health.real_pt.price.service.gymPrice;

import health.real_pt.common.BaseInterface;
import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.dto.gymPrice.GymPriceResResDto;
import health.real_pt.price.dto.gymPrice.GymPriceReqResDto;

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
    Long savePrice(GymPriceReqResDto reqDto, Long gymId);

    /**
     *  가격 수정
     */
    GymPriceResResDto updatePrice(GymPriceReqResDto reqDto);


    /**
     *  가격 삭제
     */
    void deletePrice(Long id);


    /**
     *  가격 단일 조회
     */
    GymPriceResResDto findOnePrice(Long id);

    /**
     *  가격 전체 조회
     * @return
     */
    List<GymPriceResResDto> findAllPrice(Long gymId);



}
