package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.OwnerReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.OwnerResDTO;
import kr.co.smkpetclinicstudy.service.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<String> createOwner(@Valid @RequestBody OwnerReqDTO.CREATE create) {
        try {
            ownerService.createOwner(create);
            return ResponseEntity.ok("Successfully Create Owner");
        } catch (Exception e) {
            return ResponseEntity.ok("Error : " + e);
        }
    }

    @GetMapping
    public ResponseEntity<OwnerResDTO.READ> getOwnerById(@PathVariable(name = "owner_id") Long ownerId) throws Exception {
        try {
            return ResponseEntity.ok(ownerService.getOwnerById(ownerId));
        } catch (Exception e) {
            throw new Exception("Error : " + e);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateOwner(@Valid @RequestBody OwnerReqDTO.UPDATE update) {
        try {
            ownerService.updateOwner(update);
            return ResponseEntity.ok("Successfully Update Owner");
        } catch (Exception e) {
            return ResponseEntity.ok("Error : " + e);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteOwnerById(@PathVariable(name = "owner_id") Long ownerId) {
        try {
            ownerService.deleteOwnerById(ownerId);
            return ResponseEntity.ok("Successfully Delete Owner");
        } catch (Exception e) {
            return ResponseEntity.ok("Error : " + e);
        }
    }
}
