package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.VisitReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.VisitResDTO;
import kr.co.smkpetclinicstudy.service.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping
    public void visited(@RequestBody @Valid VisitReqDTO visitReqDTO) {
        visitService.visited(visitReqDTO);
    }

    @GetMapping
    public VisitResDTO visitedInfo(@RequestParam("visitsId") Long visitsId) {
        return visitService.visitedInfo(visitsId);
    }

    @GetMapping("/all")
    public List<VisitResDTO> visitedAllInfo() {
        return visitService.visitedAllInfo();
    }

    @PutMapping
    public void updateVisitedInfo(@RequestBody @Valid VisitReqDTO visitReqDTO) {
        visitService.updateVisitedInfo(visitReqDTO);
    }

    @DeleteMapping
    public void deleteVisitedInfo(@RequestParam("visitsId") Long visitsId) {
        visitService.deleteVisitedInfo(visitsId);
    }
}
