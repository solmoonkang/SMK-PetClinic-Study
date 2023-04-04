package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import kr.co.smkpetclinicstudy.service.model.response.VetResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {

    Optional<Vet> findByVetsId(Long vetsId);

    List<VetResDTO> findVetsListBy();
}
