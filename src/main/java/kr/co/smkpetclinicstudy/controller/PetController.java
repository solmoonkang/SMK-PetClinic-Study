package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.PetReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.PetResDTO;
import kr.co.smkpetclinicstudy.service.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public void register(@RequestBody @Valid PetReqDTO petReqDto) {
        petService.register(petReqDto);
    }

    @GetMapping
    public PetResDTO getPetInfo(@RequestParam("petsId") Long petsId) {
        return petService.getPetInfo(petsId);
    }

    @GetMapping("/all")
    public List<PetResDTO> getAllPetInfo() {
        return petService.getAllPetInfo();
    }

    @PutMapping
    public void updatePetInfo(@RequestBody @Valid PetReqDTO petReqDto) {
        petService.updatePetInfo(petReqDto);
    }

    @DeleteMapping
    public void deletePetInfo(@RequestParam("petsId") Long petsId) {
        petService.deletePetInfo(petsId);
    }
}
