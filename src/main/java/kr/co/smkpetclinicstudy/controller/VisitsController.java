package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.VisitsRequest;
import kr.co.smkpetclinicstudy.service.model.response.VisitsResponse;
import kr.co.smkpetclinicstudy.service.service.VisitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitsController {

    private final VisitsService visitsService;

    @PostMapping
    public void visited(@RequestBody @Valid VisitsRequest visitsRequest) {
        visitsService.visited(visitsRequest);
    }

    @GetMapping
    public VisitsResponse visitedInfo(@RequestParam("visitsId") Long visitsId) {
        return visitsService.visitedInfo(visitsId);
    }

    @GetMapping("/all")
    public List<VisitsResponse> visitedAllInfo() {
        return visitsService.visitedAllInfo();
    }

    @PutMapping
    public void updateVisitedInfo(@RequestBody @Valid VisitsRequest visitsRequest) {
        visitsService.updateVisitedInfo(visitsRequest);
    }
}
