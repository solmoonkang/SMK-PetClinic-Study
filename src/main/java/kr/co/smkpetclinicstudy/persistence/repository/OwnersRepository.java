package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Owners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnersRepository extends JpaRepository<Owners, Long> {
    Optional<Owners> findById(String ownerId);
}
