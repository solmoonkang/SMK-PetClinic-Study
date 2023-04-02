package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Visits;
import kr.co.smkpetclinicstudy.persistence.repository.VisitsRepository;
import kr.co.smkpetclinicstudy.service.model.request.VisitsRequest;
import kr.co.smkpetclinicstudy.service.model.response.VisitsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitsService {

    private final VisitsRepository visitsRepository;

    @Transactional
    public void visited(VisitsRequest visitsRequest) {
        final Visits visits = Visits.of(visitsRequest);
        visitsRepository.save(visits);
    }

    @Transactional(readOnly = true)
    public VisitsResponse visitedInfo(Long visitsId) {
        Optional<Visits> visits = visitsRepository.findByVisitsId(visitsId);
        return Visits.of(visits.get());
    }

    @Transactional(readOnly = true)
    public List<VisitsResponse> visitedAllInfo() {
        return visitsRepository.findVisitsListBy();
    }

    @Transactional
    public void updateVisitedInfo(VisitsRequest visitsRequest) {
        Optional<Visits> visits = visitsRepository.findByVisitsId(visitsRequest.getVisitsId());
        visits.get().update(visitsRequest.getDescription());
        visitsRepository.save(visits.get());
    }

    @Transactional
    public void deleteVisitedInfo(Long visitsId) {
        visitsRepository.deleteById(visitsId);
    }
}
