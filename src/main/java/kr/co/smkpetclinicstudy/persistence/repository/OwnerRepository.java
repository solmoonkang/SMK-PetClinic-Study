package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query( "select case " +
                "when count(o) > 0 " +
                "then true else false end " +
            "from Owner o " +
            "where o.telephone = :telephone")
    boolean existsByTelephone(@Param("telephone") String telephone);
}
