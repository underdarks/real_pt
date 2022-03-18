package health.real_pt.common;

public interface BaseDto <Entity,DTO>{

    //Entity -> DTO 변환
    DTO entityToDto(Entity entity);
}
