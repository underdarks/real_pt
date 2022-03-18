package health.real_pt.common;


public interface BaseEntity<Dto> {
    /**
     * Notice
     * <p>
     * 객체 생성, 수정을 메서드 제공을 위한 공통 인터페이스
     */

    //static Entity toEntity(Dto t); //static 메소드는 상속받은 쪽에서 Overriding(재정의)이 불가능하다.
    void updateEntity(Dto dto); //객체 수정
}
