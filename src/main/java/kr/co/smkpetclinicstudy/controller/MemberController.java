package kr.co.smkpetclinicstudy.controller;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat;
import kr.co.smkpetclinicstudy.service.model.dtos.request.MemberReqDTO;
import kr.co.smkpetclinicstudy.service.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseFormat<Void> signUp(@RequestBody @Validated MemberReqDTO.CREATE create) {

        memberService.signUp(create);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_CREATED,
                create.getName() + "님 회원가입이 완료되었습니다"
        );
    }
}
