package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Member;
import kr.co.smkpetclinicstudy.persistence.repository.MemberRepository;
import kr.co.smkpetclinicstudy.service.model.dtos.request.MemberReqDTO;
import kr.co.smkpetclinicstudy.service.model.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public void signUp(MemberReqDTO.CREATE create) {

        final Member member = Member.builder()
                .identity(create.getIdentity())
                .password(create.getPassword())
                .name(create.getName())
                .roleType(RoleType.ROLE_USER)
                .build();

        memberRepository.save(member);
    }
}
