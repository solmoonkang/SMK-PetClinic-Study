package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query( "select p " +
            "from Pet p " +
            "where p.owner = :owner")
    List<Pet> findByOwner(@Param("owner") Owner owner);

    @Query( "select p " +
            "from Pet p " +
            "where p.vet = :vet")
    List<Pet> findByVet(@Param("vet") Vet vet);

    @Query( "select p " +
            "from Pet p " +
            "where p.owner.id = :ownerId")
    List<Pet> findAllByOwnerId(@Param("ownerId") Long ownerId);
}
