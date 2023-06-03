package kr.co.smkpetclinicstudy.infra.auth;

import kr.co.smkpetclinicstudy.infra.global.exception.BadRequestException;
import kr.co.smkpetclinicstudy.persistence.entity.Member;
import kr.co.smkpetclinicstudy.persistence.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {       // UserDetailsService : DB 에서 사용자 정보를 직접 가져온다

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String identity) throws UsernameNotFoundException {

        Member member = memberRepository.findByIdentity(identity)
                .orElseThrow(BadRequestException::new);

        return new CustomUserDetails(member);
    }
}
