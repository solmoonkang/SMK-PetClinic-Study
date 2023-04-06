package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.VetReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.VetResDTO;
import kr.co.smkpetclinicstudy.service.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @PostMapping()
    public ResponseEntity<String> createVet(@RequestBody @Valid VetReqDTO.CREATE create) {
        try {
            vetService.createVet(create);
            return ResponseEntity.ok("Successfully Create Vet");
        } catch (Exception e) {
            return ResponseEntity.ok("Error" + e);
        }
    }

    @GetMapping("{/vet_id}")
    public ResponseEntity<VetResDTO.READ> getVetById(@PathVariable(name = "vet_id") Long vetId) throws Exception {
        try {
            return ResponseEntity.ok(vetService.getVetById(vetId));
        } catch (Exception e) {
            throw new Exception("Error : " + e);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateVet(@RequestBody @Valid VetReqDTO.UPDATE update) {
        try {
            vetService.updateVet(update);
            return ResponseEntity.ok("Successfully Update Vet");
        } catch (Exception e) {
            return ResponseEntity.ok("Error" + e);
        }
    }

    @DeleteMapping("{/vet_id}")
    public ResponseEntity<String> deleteVetById(@PathVariable(name = "vet_id") Long vetId) {
        try {
            vetService.deleteVetById(vetId);
            return ResponseEntity.ok("Successfully Delete Vet");
        } catch (Exception e) {
            return ResponseEntity.ok("Error" + e);
        }
    }
}
