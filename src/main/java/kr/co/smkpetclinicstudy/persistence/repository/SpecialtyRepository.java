package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    @Query( "select s " +
            "from Specialty s " +
            "where s.specialtyName in :specialtyNames")
    List<Specialty> findAllBySpecialtyNameIn(@Param("specialtyNames") List<String> specialtyNames);
}
