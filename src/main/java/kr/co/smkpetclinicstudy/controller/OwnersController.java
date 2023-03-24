package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.SignUpRequest;
import kr.co.smkpetclinicstudy.service.model.response.OwnerResponse;
import kr.co.smkpetclinicstudy.service.service.OwnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnersController {

    private final OwnersService ownersService;

    @PostMapping
    public void signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        ownersService.signUp(signUpRequest);
    }

    @GetMapping
    public OwnerResponse getInfo(String ownerId) {
        return ownersService.getInfo(ownerId);
    }
}
