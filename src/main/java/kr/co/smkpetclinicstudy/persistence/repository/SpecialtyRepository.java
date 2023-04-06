package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Specialty;
import kr.co.smkpetclinicstudy.persistence.entity.VetSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository extends JpaRepository<VetSpecialty, Long> {

    List<Specialty> findAllByName(List<String> names);
}
