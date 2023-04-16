package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.persistence.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query( "select v " +
            "from Visit v " +
            "where v.pet = :pet")
    List<Visit> findByPet(@Param("pet") Pet pet);

    @Query( "select v " +
            "from Visit v " +
            "where v.pet.id = :petId")
    List<Visit> findAllByPetId(@Param("petId") Long petId);
}
