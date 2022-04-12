package health.real_pt.common;

public interface BaseDto <Entity,DTO>{
    /**
     * Notice
     *
     * DTO 공통 인터페이스
     */

    //Entity -> DTO 변환
    DTO entityToDto(Entity entity);
}
