package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.VetRequest;
import kr.co.smkpetclinicstudy.service.service.VetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetsController {

    private final VetsService vetsService;

    @PostMapping()
    public void signUp(@RequestBody @Valid VetRequest vetRequest) {
        vetsService.signUp(vetRequest);
    }
}
