package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Visit;
import kr.co.smkpetclinicstudy.persistence.repository.VisitRepository;
import kr.co.smkpetclinicstudy.service.model.request.VisitReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.VisitResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    @Transactional
    public void visited(VisitReqDTO visitReqDTO) {
        final Visit visit = Visit.of(visitReqDTO);
        visitRepository.save(visit);
    }

    @Transactional(readOnly = true)
    public VisitResDTO visitedInfo(Long visitsId) {
        Optional<Visit> visits = visitRepository.findByVisitsId(visitsId);
        return Visit.of(visits.get());
    }

    @Transactional(readOnly = true)
    public List<VisitResDTO> visitedAllInfo() {
        return visitRepository.findVisitsListBy();
    }

    @Transactional
    public void updateVisitedInfo(VisitReqDTO visitReqDTO) {
        Optional<Visit> visits = visitRepository.findByVisitsId(visitReqDTO.getVisitsId());
        visits.get().update(visitReqDTO.getDescription());
        visitRepository.save(visits.get());
    }

    @Transactional
    public void deleteVisitedInfo(Long visitsId) {
        visitRepository.deleteById(visitsId);
    }
}
