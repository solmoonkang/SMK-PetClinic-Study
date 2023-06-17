package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.exception.DuplicatedException;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.persistence.entity.Member;
import kr.co.smkpetclinicstudy.persistence.repository.MemberRepository;
import kr.co.smkpetclinicstudy.service.model.dtos.request.MemberReqDTO;
import kr.co.smkpetclinicstudy.service.model.enums.RoleType;
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

    @Transactional
    public void signUp(MemberReqDTO.CREATE create) {

        String rawPassword = create.getPassword();

        String encPassword = passwordEncoder.encode(rawPassword);

//        validateDuplicateMember(create.getIdentity());

        final Member member = Member.builder()
                .identity(create.getIdentity())
                .password(encPassword)
                .name(create.getName())
                .roleType(RoleType.USER_ROLE)
                .build();

        memberRepository.save(member);
    }


    // 사용자 ID 중복 확인
//    private void validateDuplicateMember(String identity) {
//
//        final Member member = memberRepository.findByIdentity(identity)
//                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER));
//
//        if (member != null) {
//            throw new DuplicatedException(ErrorCode.DUPLICATED_MEMBER_ID);
//        }
//    }
}
