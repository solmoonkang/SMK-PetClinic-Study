package kr.co.smkpetclinicstudy.controller;

import kr.co.smkpetclinicstudy.service.service.VisitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visits")
@RequiredArgsConstructor
public class VisitsController {

    private final VisitsService visitsService;
}
