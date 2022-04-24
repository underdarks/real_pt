package health.real_pt.security.config;

import health.real_pt.exception.exception_handler.ExceptionType;
import health.real_pt.exception.exceptions.CommonApiExceptions;
import health.real_pt.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //userId를 활용하여 Member를 찾는다.
        return memberRepository.findByUserId(username).orElseThrow(() -> new CommonApiExceptions(ExceptionType.LOGIN_FAILED,"사용자를 찾지 못하였습니다."));
    }
}
