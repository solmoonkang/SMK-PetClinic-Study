package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Pets;
import kr.co.smkpetclinicstudy.service.model.response.PetResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetsRepository extends JpaRepository<Pets, Long> {

    Optional<Pets> findByPetsId(Long petsId);

    List<PetResponse> findPetsListBy();
}
