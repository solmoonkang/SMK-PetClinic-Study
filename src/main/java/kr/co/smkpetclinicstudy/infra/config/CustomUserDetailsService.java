package kr.co.smkpetclinicstudy.infra.config;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.persistence.entity.Member;
import kr.co.smkpetclinicstudy.persistence.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userIdentity) throws UsernameNotFoundException {

        Member member = memberRepository.findByIdentity(userIdentity)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER));

        return toUserDetails(member);
    }


    private UserDetails toUserDetails(Member member) {

        return User.builder()
                .username(member.getIdentity())
                .password(member.getPassword())
                .authorities(new SimpleGrantedAuthority(member.getRoleType().getRoleType()))
                .build();
    }
}
