package health.real_pt.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Spring Security 사용을 위한 설정
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 암호화에 필요한 PasswordEncode를 Bean으로 등록
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * AuthenticationManager를 Bean으로 등록
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()  //rest api 고려
                .csrf().disable()   //csrf 보안 토큰 disable 처리
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //토큰 기반 인증이라 세션 사용하지 않는다
                .and()
                .authorizeRequests()
//                .antMatchers("/api/v1/member/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/api/v1/member/**").hasRole("USER")       //GET /api/v1/member/** uri 주소의 요청에 대한 인증 요구
                .antMatchers(HttpMethod.DELETE,"/api/v1/member/**").hasRole("USER")    //DELETE /api/v1/member/** uri 주소의 요청에 대한 인증 요구
                .antMatchers(HttpMethod.PATCH,"/api/v1/member/**").hasRole("USER")     //PATCH /api/v1/member/** uri 주소의 요청에 대한 인증 요구
                .anyRequest().permitAll()   //그외 나머지 요청은 누구나 접근 가능
                .and()
                .addFilterBefore(          //JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
                        new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class
                );


    }
}

