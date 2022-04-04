package health.real_pt.price.service.GymPrice;

import health.real_pt.price.api.GymPrice.GymPriceListDto;
import health.real_pt.price.api.GymPrice.GymPriceResDto;
import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.dto.GymPrice.GymPriceDto;

import java.util.List;
import java.util.Optional;

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
    Long saveGymPrice(GymPriceDto gymPriceDto, Long gymId);

    /**
     *  가격 수정
     */
    GymPriceResDto updateGymPrice(GymPriceDto gymPriceDto);


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
     */
    GymPriceListDto findAllPrice(Long gymId);



}
