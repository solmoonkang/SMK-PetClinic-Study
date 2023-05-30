package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.service.model.enums.PetType;
import kr.co.smkpetclinicstudy.service.model.dtos.request.PetReqDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "tbl_pets",
        indexes = @Index(name = "i_pets", columnList = "pet_id")
)
@AttributeOverride(
        name = "id",
        column = @Column(name = "pet_id", length = 4)
)
public class Pet extends BaseEntity {

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "pets_types")
    @Enumerated(value = EnumType.STRING)
    private PetType petType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @Builder
    private Pet(String name,
               LocalDate birthDate,
               PetType petType,
               Owner owner,
                Vet vet) {
        this.name = name;
        this.birthDate = birthDate;
        this.petType = petType;
        this.owner = owner;
        this.vet = vet;
    }


    public void updatePet(PetReqDTO.UPDATE update) {
        this.name = update.getName();
        this.birthDate = update.getBirthDate();
        this.petType = PetType.of(update.getPetType());
    }
}