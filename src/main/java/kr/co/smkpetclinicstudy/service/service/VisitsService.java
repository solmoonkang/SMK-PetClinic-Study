package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Visits;
import kr.co.smkpetclinicstudy.persistence.repository.VisitsRepository;
import kr.co.smkpetclinicstudy.service.model.request.VisitsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VisitsService {

    private final VisitsRepository visitsRepository;

    @Transactional
    public void visited(VisitsRequest visitsRequest) {
        final Visits visits = Visits.of(visitsRequest);
        visitsRepository.save(visits);
    }
}
