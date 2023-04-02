package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.OwnersRequest;
import kr.co.smkpetclinicstudy.service.model.response.OwnersResponse;
import kr.co.smkpetclinicstudy.service.service.OwnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnersController {

    private final OwnersService ownersService;

    @PostMapping
    public void signUp(@Valid @RequestBody OwnersRequest ownersRequest) {
        ownersService.signUp(ownersRequest);
    }

    @GetMapping
    public OwnersResponse getInfo(@RequestParam("id") Long id) {
        return ownersService.getInfo(id);
    }

    @GetMapping("/all")
    public List<OwnersResponse> getAllInfo() {
         return ownersService.getAllInfo();
    }

    @PutMapping
    public void editInfo(@Valid @RequestBody OwnersRequest ownersRequest) {
        ownersService.editInfo(ownersRequest);
    }

    @DeleteMapping
    public void deleteInfo(@Valid @RequestBody Long ownerId) {
        ownersService.deleteInfo(ownerId);
    }
}
