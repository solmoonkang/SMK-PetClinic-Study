package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.service.model.request.PetReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.PetResDTO;
import kr.co.smkpetclinicstudy.service.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<String> createPet(@RequestBody @Valid PetReqDTO.CREATE create) {
        try {
            petService.createPet(create);
            return ResponseEntity.ok("Successfully Create Pet");
        } catch (Exception e) {
            return ResponseEntity.ok("Error : " + e);
        }
    }

    @GetMapping("/{owner_id}")
    public ResponseEntity<List<PetResDTO.READ>> getPetsByOwnerId(@PathVariable(name = "pet_id") Long ownerId) throws Exception {
        try {
            return ResponseEntity.ok(petService.getPetsByOwnerId(ownerId));
        } catch (Exception e) {
            throw new Exception("Error : " + e);
        }
    }

    @PutMapping
    public ResponseEntity<String> updatePet(@RequestBody @Valid PetReqDTO.UPDATE update) {
        try {
            petService.updatePet(update);
            return ResponseEntity.ok("Successfully Update Pet");
        } catch (Exception e) {
            return ResponseEntity.ok("Error : " + e);
        }
    }

    @DeleteMapping("/{pet_id}")
    public ResponseEntity<String> deletePet(@PathVariable(name = "pet_id") Long petId) {
        try {
            petService.deletePetById(petId);
            return ResponseEntity.ok("Successfully Create Pet");
        } catch (Exception e) {
            return ResponseEntity.ok("Error : " + e);
        }
    }
}
