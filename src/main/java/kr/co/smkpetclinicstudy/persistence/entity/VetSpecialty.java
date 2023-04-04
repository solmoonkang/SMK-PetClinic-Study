package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_vets_specialties")
@AttributeOverride(
        name = "id",
        column = @Column(name = "vets_specialties_id", length = 4))
public class VetSpecialty extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vets_id")
    private Vet vet;

    @JoinColumn(name = "specialties_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Specialty specialty;
}
