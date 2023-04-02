package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.VetsRequest;
import kr.co.smkpetclinicstudy.service.model.response.VetsResponse;
import kr.co.smkpetclinicstudy.service.service.VetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetsController {

    private final VetsService vetsService;

    @PostMapping()
    public void signUp(@RequestBody @Valid VetsRequest vetsRequest) {
        vetsService.signUp(vetsRequest);
    }

    @GetMapping
    public VetsResponse getInfo(@RequestParam("vetsId") Long vetsId) {
        return vetsService.getInfo(vetsId);
    }

    @GetMapping("all")
    public List<VetsResponse> getAllInfo() {
        return vetsService.getAllInfo();
    }

    @PutMapping
    public void updateVetInfo(@RequestBody @Valid VetsRequest vetsRequest) {
        vetsService.updateVetInfo(vetsRequest);
    }

    @DeleteMapping
    public void deleteVetInfo(@RequestParam("vetsId") Long vetsId) {
        vetsService.deleteVetInfo(vetsId);
    }
}
