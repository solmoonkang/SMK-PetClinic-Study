package kr.co.smkpetclinicstudy.controller;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.error.response.ResponseFormat;
import kr.co.smkpetclinicstudy.service.model.dtos.request.MemberReqDTO;
import kr.co.smkpetclinicstudy.service.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String main() {

        return "main-page";
    }

    @GetMapping("/user")
    public String user() {

        return "user-page";
    }

    @GetMapping("/admin")
    public String admin() {

        return "admin-page";
    }

    @GetMapping("/fail")
    public String fail() {

        return "login-fail";
    }

    @PostMapping("/signUp")
    public ResponseFormat<String> signUp(@RequestBody @Validated MemberReqDTO.CREATE create) {

        memberService.signUp(create);

        return ResponseFormat.successMessage(
                ErrorCode.SUCCESS_CREATED,
                create.getName() + "님 성공적으로 회원가입 되었습니다"
        );
    }
}
