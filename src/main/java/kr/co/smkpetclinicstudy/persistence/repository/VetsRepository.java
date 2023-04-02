package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Vets;
import kr.co.smkpetclinicstudy.service.model.response.VetsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetsRepository extends JpaRepository<Vets, Long> {

    List<VetsResponse> findVetsListBy();
}
