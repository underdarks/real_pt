package health.real_pt.price.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.member.domain.Member;
import health.real_pt.price.domain.GymPrice;
import health.real_pt.price.dto.GymPriceDto;

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
    Long saveGymPrice(GymPriceDto gymPriceDto, Long memberId, Long gymId);

    /**
     *  가격 수정
     */
    void updateGymPrice(GymPriceDto gymPriceDto);


    /**
     *  가격 삭제
     */
    void deleteGymPrice(GymPriceDto gymPriceDto);


    /**
     *  가격 단일 조회
     */
    Optional<GymPrice> findOnePrice(Long gymPriceId);

    /**
     *  가격 전체 조회
     */
    List<GymPrice> findAllPrice();



}
