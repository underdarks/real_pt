package health.real_pt.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Http 응답 코드 공통 클래스
 */
public class StatusCode {

    /**
     * 2xx : 요청을 성공적으로 받았으며 인식했고 수용했음(Success)
     */

    public static final int OK = 200;           //요청을 정상적으로 처리함
    public static final int CREATED = 201;      //성공적으로 생성에 대한 요청을 받었으며 서버가 새 리소스를 작성함 (대개 POST, PUT일 때)
    public static final int NO_CONTENT = 204;   //요청을 성공적으로 처리했지만 제공할 컨텐츠가 없음

    /**
     * 4xx : 클라이언트의 잘못된 요청
     */
    public static final int BAD_REQUEST = 400;  //잘못된 뭄버으로 요청을 보냄
    public static final int UNAUTHORIZED = 401; //요청을 위한 권한 인증 필요(ex. 토큰 없음)
    public static final int FORBIDDEN = 403;    //요청한 컨테츠에 대한 접근 권리가 없음
    public static final int NOT_FOUND = 404;    //요청한 URI 찾을 수 없음

    /**
     * 5xx : 서버에러
     */
    public static final int INTERNAL_SERVER_ERROR = 500;    //서버 문제로 응답 못함
    public static final int SERVICE_UNAVAILABLE = 503;      //서버 일시적으로 사용 불가

    //DB 에러
    public static final int DB_ERROR = 600;

}
