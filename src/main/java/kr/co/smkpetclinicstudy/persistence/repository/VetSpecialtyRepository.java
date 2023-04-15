package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.VetSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetSpecialtyRepository extends JpaRepository<VetSpecialty, Long> {
}
