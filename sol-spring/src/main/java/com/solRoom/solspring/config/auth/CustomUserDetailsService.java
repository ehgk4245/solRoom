package com.solRoom.solspring.config.auth;

import com.solRoom.solspring.config.auth.CustomUserDetails;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
// 시큐리티 설정에서 loginProcessingURL("/login")
// "/login" 요청이 오면 자동으로 UserDetailsService타입으로 IoC되어있는 loadUserByUsername 함수가 실행
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member memberData = memberRepository.findByEmail(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
                });
        return new CustomUserDetails(memberData);
    }
}
