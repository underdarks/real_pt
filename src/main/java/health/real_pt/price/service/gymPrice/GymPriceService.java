package health.real_pt.price.service.gymPrice;

import health.real_pt.price.dto.gymPrice.GymPriceResDto;
import health.real_pt.price.dto.gymPrice.GymPriceReqDto;

import java.util.List;

public interface GymPriceService {

    /**
     *              Notice
     *
     * 헬스장 가격(GYM_PRICE) Service
     * 구현체 => GymPriceServiceImpl.java
     */

    /**
     *  가격 저장
     */
    Long saveGymPrice(GymPriceReqDto gymPriceReqDto, Long gymId);

    /**
     *  가격 수정
     */
    GymPriceResDto updateGymPrice(GymPriceReqDto gymPriceReqDto);


    /**
     *  가격 삭제
     */
    void deleteGymPrice(Long id);


    /**
     *  가격 단일 조회
     */
    GymPriceResDto findOnePrice(Long gymPriceId);

    /**
     *  가격 전체 조회
     * @return
     */
    List<GymPriceResDto> findAllPrice(Long gymId);



}
