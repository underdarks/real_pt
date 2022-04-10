package health.real_pt.common;


/**
 * 응답 메시지 공통 클래스
 */
public class ResponseMessage {

    /**
     *  회원 관련 응답 메시지
     */
    public static final String CREATED_USER_SUCCESS = "회원 가입 성공";
    public static final String CREATED_USER_FAIL = "회원 가입 실패";

    public static final String READ_USER_SUCCESS = "회원 조회 성공";
    public static final String READ_USER_FAIL = "회원 조회 실패";

    public static final String UPDATE_USER_SUCCESS = "회원 수정 성공";
    public static final String UPDATE_USER_FAIL = "회원 수정 실패";

    public static final String DELETE_USER_SUCCESS = "회원 탈퇴 성공";
    public static final String DELETE_USER_FAIL = "회원 탈퇴 실패";

    //===============================================================






    /**
     * 기타 에러
     */

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";

}
