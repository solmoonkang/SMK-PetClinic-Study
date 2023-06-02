package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.exception.BadRequestException;
import kr.co.smkpetclinicstudy.infra.global.exception.DuplicatedException;
import kr.co.smkpetclinicstudy.persistence.entity.Member;
import kr.co.smkpetclinicstudy.persistence.repository.MemberRepository;
import kr.co.smkpetclinicstudy.service.model.dtos.request.MemberReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * Member Sign Up Service
     * 진행 방향만 설정 : 이런 식으로 구현하지 않을까 생각
     */
    @Transactional
    public void signUp(MemberReqDTO.CREATE create) {

        // 입력받은 인코딩 되지 않은 비밀번호
        String rawPassword = create.getPassword();

        // 인코딩 된 비밀번호
        String encPassword = passwordEncoder.encode(rawPassword);

        final Member member = Member.builder()
                .identity(create.getIdentity())
                .password(encPassword)
                .name(create.getName())
                .roleType(create.getRoleType())
                .build();

        // 중복된 ID 있을 경우, 중복 예외 발생
        checkIdentityExists(member.getIdentity(), create.getIdentity());

        memberRepository.save(member);
    }


    // TODO : 기존 아이디와 회원가입 시 입력한 아이디 중복검사 : 수정 필요
    private void checkIdentityExists(String identity, String newIdentity) {

        // 기존 아이디가 있는지 저장소에서 existsBy 메서드를 통해 찾고, user 객체를 생성
        final Member member = memberRepository.existsByIdentity(identity)
                .orElseThrow(BadRequestException::new);

        // 생성된 user 객체의 아이디와 입력받은 아이디가 동일한 비교
        if (member.getIdentity().equals(newIdentity)) {
            // 만약, 동일할 경우 중복 예외가 발생
            throw new DuplicatedException(ErrorCode.DUPLICATED_MEMBER_ID);
        }
    }
}
