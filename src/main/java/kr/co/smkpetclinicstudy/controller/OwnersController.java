package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.OwnerRequest;
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
    public void signUp(@Valid @RequestBody OwnerRequest ownerRequest) {
        ownersService.signUp(ownerRequest);
    }

    @GetMapping
    public OwnerResponse getInfo(@RequestParam("ownerId") String ownerId) {
        return ownersService.getInfo(ownerId);
    }

    @PutMapping
    public void editInfo(@Valid @RequestBody OwnerRequest ownerRequest) {
        ownersService.editInfo(ownerRequest);
    }

    @DeleteMapping
    public void deleteInfo(@Valid @RequestBody Long ownerId) {
        ownersService.deleteInfo(ownerId);
    }
}
