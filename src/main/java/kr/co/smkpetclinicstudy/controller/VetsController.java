package kr.co.smkpetclinicstudy.controller;

import kr.co.smkpetclinicstudy.service.service.VetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vets")
@RequiredArgsConstructor
public class VetsController {

    private final VetsService vetsService;
}
