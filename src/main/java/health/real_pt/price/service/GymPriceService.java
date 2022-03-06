package health.real_pt.price.service;

import health.real_pt.gym.domain.Gym;
import health.real_pt.member.domain.Member;
import health.real_pt.price.dto.GymPriceDto;

public interface GymPriceService {

    /**
     *              Notice
     *
     * 구현체 => GymPriceServiceImpl.java
     */

    Long saveGymPrice(GymPriceDto gymPriceDto, Long memberId, Long gymId);

    void



}
