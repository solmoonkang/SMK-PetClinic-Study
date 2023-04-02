package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Visits;
import kr.co.smkpetclinicstudy.service.model.response.VisitsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitsRepository extends JpaRepository<Visits, Long> {

    Optional<Visits> findByVisitsId(Long visitsId);

    List<VisitsResponse> findVisitsListBy();
}
