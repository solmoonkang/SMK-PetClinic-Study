package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.repository.VisitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitsService {

    private final VisitsRepository visitsRepository;
}
