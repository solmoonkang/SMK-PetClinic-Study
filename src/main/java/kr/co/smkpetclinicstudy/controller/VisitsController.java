package kr.co.smkpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.smkpetclinicstudy.service.model.request.VisitsRequest;
import kr.co.smkpetclinicstudy.service.service.VisitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitsController {

    private final VisitsService visitsService;

    @PostMapping
    public void visited(@RequestBody @Valid VisitsRequest visitsRequest) {
        visitsService.visited(visitsRequest);
    }
}
