package kr.co.smkpetclinicstudy.infra.auth;

import kr.co.smkpetclinicstudy.persistence.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {     // UserDetails : 사용자 정보를 담는 인터페이스

    private final Member member;

    private final boolean isAccountExpired;

    private final boolean isAccountLocked;

    private final boolean isCredentialsExpired;

    private final boolean isEnabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(member.getRoleType().getRoleType()));

        return authorities;
    }

    @Override
    public String getPassword() {

        return member.getPassword();
    }

    @Override
    public String getUsername() {

        return member.getIdentity();
    }

    @Override
    public boolean isAccountNonExpired() {

        return !isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {

        return !isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return !isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {

        return isEnabled;
    }


    public CustomUserDetails(Member member) {

        this.member = member;
        this.isAccountExpired = false;
        this.isAccountLocked = false;
        this.isCredentialsExpired = false;
        this.isEnabled = false;
    }
}
