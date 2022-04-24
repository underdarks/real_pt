package health.real_pt.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String secretKey="EzSECxODSLQPWOOMzdfW";


    //토큰 유효시간 30분
    private Long tokenValidTime= 30 * 60 * 1000L;

    private final UserDetailsService userDetailsService;

    //secretKey Base64로 인코딩
    @PostConstruct
    protected void init(){
        secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     *  JWT 토큰 생성
     *  JWT 토큰 구성 = 헤더(Header).내용(payload).서명(signature)
     *
     *  헤더(Header)는 두가지 정보를 갖는다
     *   -> typ : 토큰의 타입 지정
     *   -> alg : 해싱 알고리즘 선택(어떤 해싱 알고리즘 사용하는지, 보통 SHA256, RSA 사용), 이 알고리즘은 토큰 검증 할 때 사용되는 signature 부분에서 사용된다
     *
     *  내용(payload)
     *   -> 토큰에 담을 정보가 들어있다. 정보의 한 조각을 클레임(Claim)이라고 부르고 key:value의 쌍으로 구성되어 있음
     *   -> 클레임의 종류는 3가지로 구분된다. 등록된(Registered) 클레임, 공개(Public) 클레임, 비공개(Private) 클레임
     *
     *   ex.
     *   {
     *      "iss": "llshl.com",
     *      "exp": "1485270000000",
     *      "https://example.com/jwt_claims/is_admin": true,
     *      "userId": "12313124",
     *      "username": "text"
     *   }
     *
     *  등록된(Registered) 클레임
     *   -> 서비스에 필요한 정보가 아니라 토큰에 대한 정보를 담기위해 이름이 이미 정해진 클레임들. 등록된 클레임은 Optional 함
     *    {
     *      iss : 토큰 발급자 (issuer)
     *      sub : 토큰 제목 (subject)
     *      aud : 토큰 대상자 (audience)
     *      exp : 토큰의 만료시간 (expiraton), 시간은 NumericDate 형식으로 되어있어야 하며 (예: 1480849147370) 언제나 현재 시간보다 이후로 설정돼야한다.
     *      nbf : Not Before 를 의미하며, 토큰의 활성 날짜와 비슷한 개념. 여기에도 NumericDate 형식으로 날짜를 지정하며, 이 날짜가 지나기 전까지는 토큰이 처리되지 않는다.
     *      iat : 토큰이 발급된 시간 (issued at), 이 값을 사용하여 토큰의 age 가 얼마나 되었는지 판단 할 수 있다.
     *      jti : JWT의 고유 식별자로서, 주로 중복적인 처리를 방지하기 위하여 사용된다. 일회용 토큰에 사용하면 유용.
     *    }
     *
     *
     *  공개(Public) 클레임
     *   -> 공개 클레임은 충돌이 방지된 이름을 가지고 있어야 함. 충돌 방지를 위해 클레임 이름을 URI 형식으로 짓는다
     *   ex.{"https://velopert.com/jwt_claims/is_admin": true}
     *
     *  비공개(Private) 클레임
     *   -> 클라이언트 <-> 서버 간의 협의하에 사용되는 클레임 이름들이다. 중복 가능으로 충돌이 될 수 있으니 사용시 유의
     *
     *
     *  서명(signature)
     *   -> 헤더의 인코딩값과, 정보의 인코딩값을 합친 후 주어진 비밀키로 해싱하여 생성.
     *   서버는 요청으로 토큰을 받으면 헤더와 페이로드의 값을 서버의 비밀키와 함께 돌려서 계산된 결과값이 서명값과 일치하는지 확인한다
     *
     *   => JWT는 세션과 다르게 저장하지 않고 서명 부분을 인증만하여 유효한지 확인
     */
    public String createJwtToken(String userId, List<String> roles){
        Claims claims= Jwts.claims().setSubject(userId);
        claims.put("roles",roles);  //정보는 key : value 쌍으로 지정
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)  //정보 저장
                .setIssuedAt(now)   //토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime))    //토큰 만기 시간
                .signWith(SignatureAlgorithm.HS256,secretKey)   //사용 알고리즘과 서명에 들어갈 secretkey 세팅
                .compact();
    }


    /**
     * JWT 토큰에서 인증 정보 조회
     */
    public Authentication getAuthentication(String token){
        //userId로 Member 찾는다
        UserDetails userDetails= userDetailsService.loadUserByUsername(getUserPk(token));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

        return authenticationToken;
    }

    /**
     *  토큰에서 회원 정보 추출
     */
    public String getUserPk(String token){
        String subject = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        return subject; //userID반환
    }

    /**
     *  Request의 Header에서 token 값을 가져온다.
     *  ("X-AUTH-TOKEN" : "TOKEN 값")
     */
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    /**
     * 토큰 유효성 + 만료일자 확인
     */
    public boolean validateToken(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        }
        catch (Exception e) {
            return false;
        }

    }


}
