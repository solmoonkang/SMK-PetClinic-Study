package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VisitResDTO;
import kr.co.smkpetclinicstudy.service.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<String> createVisit(@RequestBody @Valid VisitReqDTO.CREATE create) {
        try {
            visitService.createVisit(create);
            return ResponseEntity.ok("Successfully Create Visit");
        } catch (Exception e) {
            return ResponseEntity.ok("Error : " + e);
        }
    }

    @GetMapping("/{pet_id}")
    public ResponseEntity<List<VisitResDTO.READ>> getVisitByPetId(@PathVariable(name = "pet_id") Long petId) throws Exception {
        try {
            return ResponseEntity.ok(visitService.getVisitByPetId(petId));
        } catch (Exception e) {
            throw new Exception("Error : " + e);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateVisit(@RequestBody @Valid VisitReqDTO.UPDATE update) {
        try {
            visitService.updateVisit(update);
            return ResponseEntity.ok("Successfully Update Visit");
        } catch (Exception e) {
            return ResponseEntity.ok("Error : " + e);
        }
    }

    @DeleteMapping("/{visit_id}")
    public ResponseEntity<String> deleteVisitById(@PathVariable(name = "visit_id") Long visitId) {
        try {
            visitService.deleteVisitById(visitId);
            return ResponseEntity.ok("Successfully Delete Visit");
        } catch (Exception e) {
            return ResponseEntity.ok("Error : " + e);
        }
    }
}
