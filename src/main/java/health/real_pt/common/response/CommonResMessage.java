package health.real_pt.common.response;


/**
 * 응답 메시지 공통 클래스
 */
public class CommonResMessage {

    /**
     *  회원 관련 응답 메시지
     */
    public static final String CREATED_USER_SUCCESS = "회원 가입 성공";
    public static final String CREATED_USER_FAIL = "회원 가입 실패";

    public static final String READ_USER_SUCCESS = "회원 조회 성공";
    public static final String READ_USER_FAIL = "회원 조회 실패";

    public static final String READ_ALL_USER_SUCCESS = "전체 회원 조회 성공";
    public static final String READ_ALL_USER_FAIL = "전체 회원 조회 실패";

    public static final String UPDATE_USER_SUCCESS = "회원 수정 성공";
    public static final String UPDATE_USER_FAIL = "회원 수정 실패";

    public static final String DELETE_USER_SUCCESS = "회원 탈퇴 성공";
    public static final String DELETE_USER_FAIL = "회원 탈퇴 실패";

    //===============================================================

    /**
     *  헬스장 관련 응답 메시지
     */
    public static final String CREATED_GYM_SUCCESS = "헬스장 정보 등록 성공";
    public static final String CREATED_GYM_FAIL = "헬스장 정보 등록 실패";

    public static final String READ_GYM_SUCCESS = "헬스장 정보 조회 성공";
    public static final String READ_GYM_FAIL = "헬스장 정보 조회 실패";

    public static final String READ_ALL_GYM_SUCCESS = "전체 핼스장 조회 성공";
    public static final String READ_ALL_GYM_FAIL = "전체 헬스장 조회 실패";

    public static final String UPDATE_GYM_SUCCESS = "헬스장 정보 수정 성공";
    public static final String UPDATE_GYM_FAIL = "헬스장 정보 수정 실패";

    public static final String DELETE_GYM_SUCCESS = "헬스장 정보 삭제 성공";
    public static final String DELETE_GYM_FAIL = "헬스장 정보 삭제 실패";


    //===============================================================

    /**
     *  헬스장 가격 관련 응답 메시지
     */
    public static final String CREATED_GYM_PRICE_SUCCESS = "헬스장 가격 정보 등록 성공";
    public static final String CREATED_GYM_PRICE_FAIL = "헬스장 가격 정보 등록 실패";

    public static final String READ_GYM_PRICE_SUCCESS = "헬스장 가격 정보 조회 성공";
    public static final String READ_GYM_PRICE_FAIL = "헬스장 가격 정보 조회 실패";

    public static final String READ_ALL_GYM_PRICE_SUCCESS = "전체 핼스장 가격 조회 성공";
    public static final String READ_ALL_GYM_PRICE_FAIL = "전체 헬스장 가격 조회 실패";

    public static final String UPDATE_GYM_PRICE_SUCCESS = "헬스장 기격 정보 수정 성공";
    public static final String UPDATE_GYM_PRICE_FAIL = "헬스장 기격 정보 수정 실패";

    public static final String DELETE_GYM_PRICE_SUCCESS = "헬스장 가격 정보 삭제 성공";
    public static final String DELETE_GYM_PRICE_FAIL = "헬스장 가격 정보 삭제 실패";

    //===============================================================

    /**
     *  PT 가격 관련 응답 메시지
     */
    public static final String CREATED_PT_PRICE_SUCCESS = "PT 가격 정보 등록 성공";
    public static final String CREATED_PT_PRICE_FAIL = "PT 가격 정보 등록 실패";

    public static final String READ_PT_PRICE_SUCCESS = "PT 가격 정보 조회 성공";
    public static final String READ_PT_PRICE_FAIL = "PT 가격 정보 조회 실패";

    public static final String READ_ALL_PT_PRICE_SUCCESS = "전체 PT 가격 조회 성공";
    public static final String READ_ALL_PT_PRICE_FAIL = "전체 PT 가격 조회 실패";

    public static final String UPDATE_PT_PRICE_SUCCESS = "PT 기격 정보 수정 성공";
    public static final String UPDATE_PT_PRICE_FAIL = "PT 기격 정보 수정 실패";

    public static final String DELETE_PT_PRICE_SUCCESS = "PT 가격 정보 삭제 성공";
    public static final String DELETE_PT_PRICE_FAIL = "PT 가격 정보 삭제 실패";






    /**
     * 기타 에러
     */

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";

}
