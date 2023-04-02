package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.PetsRequest;
import kr.co.smkpetclinicstudy.service.model.response.PetsResponse;
import kr.co.smkpetclinicstudy.service.service.PetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetsController {

    private final PetsService petsService;

    @PostMapping
    public void register(@RequestBody @Valid PetsRequest petsRequest) {
        petsService.register(petsRequest);
    }

    @GetMapping
    public PetsResponse getPetInfo(@RequestParam("petsId") Long petsId) {
        return petsService.getPetInfo(petsId);
    }

    @GetMapping("/all")
    public List<PetsResponse> getAllPetInfo() {
        return petsService.getAllPetInfo();
    }

    @PutMapping
    public void updatePetInfo(@RequestBody @Valid PetsRequest petsRequest) {
        petsService.updatePetInfo(petsRequest);
    }

    @DeleteMapping
    public void deletePetInfo(@RequestParam("petsId") Long petsId) {
        petsService.deletePetInfo(petsId);
    }
}
