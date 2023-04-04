package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Visit;
import kr.co.smkpetclinicstudy.service.model.response.VisitResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Optional<Visit> findByVisitsId(Long visitsId);

    List<VisitResDTO> findVisitsListBy();
}
