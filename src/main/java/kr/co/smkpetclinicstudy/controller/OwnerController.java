package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.OwnerReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.OwnerResDTO;
import kr.co.smkpetclinicstudy.service.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public void signUp(@Valid @RequestBody OwnerReqDTO ownerReqDto) {
        ownerService.signUp(ownerReqDto);
    }

    @GetMapping
    public OwnerResDTO getInfo(@RequestParam("ownerId") Long ownerId) {
        return ownerService.getInfo(ownerId);
    }

    @GetMapping("/all-owners")
    public List<OwnerResDTO> getAllInfo() {
         return ownerService.getAllInfo();
    }

    @PutMapping
    public void editInfo(@Valid @RequestBody OwnerReqDTO ownerReqDto) {
        ownerService.editInfo(ownerReqDto);
    }

    @DeleteMapping
    public void deleteInfo(@RequestParam("ownerId") Long ownerId) {
        ownerService.deleteInfo(ownerId);
    }
}
