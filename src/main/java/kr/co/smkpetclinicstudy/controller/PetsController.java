package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.PetRequest;
import kr.co.smkpetclinicstudy.service.model.response.PetResponse;
import kr.co.smkpetclinicstudy.service.service.PetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetsController {

    private final PetsService petsService;

    @PostMapping
    public void register(@RequestBody @Valid PetRequest petRequest) {
        petsService.register(petRequest);
    }

    @GetMapping
    public PetResponse getPetInfo(Long petsId) {
        return petsService.getPetInfo(petsId);
    }
}
