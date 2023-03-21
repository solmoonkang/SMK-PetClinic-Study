package kr.co.smkpetclinicstudy.persistence.repository;

import kr.co.smkpetclinicstudy.persistence.entity.Owners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnersRepository extends JpaRepository<Owners, Long> {
}
