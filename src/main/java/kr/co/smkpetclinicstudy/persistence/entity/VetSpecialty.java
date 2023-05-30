package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "tbl_vets_specialties",
        indexes = @Index(name = "i_vets_specialties", columnList = "vet_specialty_id")
)
@AttributeOverride(
        name = "id",
        column = @Column(name = "vet_specialty_id", length = 4)
)
public class VetSpecialty extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Builder
    private VetSpecialty(Vet vet,
                        Specialty specialty) {
        this.vet = vet;
        this.specialty = specialty;
    }
}
