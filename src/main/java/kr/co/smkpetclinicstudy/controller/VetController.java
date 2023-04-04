package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.VetReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.VetResDTO;
import kr.co.smkpetclinicstudy.service.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @PostMapping()
    public void signUp(@RequestBody @Valid VetReqDTO vetReqDTO) {
        vetService.signUp(vetReqDTO);
    }

    @GetMapping
    public VetResDTO getInfo(@RequestParam("vetsId") Long vetsId) {
        return vetService.getInfo(vetsId);
    }

    @GetMapping("all")
    public List<VetResDTO> getAllInfo() {
        return vetService.getAllInfo();
    }

    @PutMapping
    public void updateVetInfo(@RequestBody @Valid VetReqDTO vetReqDTO) {
        vetService.updateVetInfo(vetReqDTO);
    }

    @DeleteMapping
    public void deleteVetInfo(@RequestParam("vetsId") Long vetsId) {
        vetService.deleteVetInfo(vetsId);
    }
}
