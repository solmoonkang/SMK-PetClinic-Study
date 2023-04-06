package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.persistence.enums.PetType;
import kr.co.smkpetclinicstudy.service.model.request.PetReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.PetResDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_pets")
@AttributeOverride(
        name = "id",
        column = @Column(name = "pet_id", length = 4))
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


    @Builder
    public Pet(String name,
               LocalDate birthDate,
               PetType petType,
               Owner owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.petType = petType;
        this.owner = owner;
    }


    public static Pet dtoToEntity(PetReqDTO.CREATE create,
                                  Owner owner) {
        return Pet.builder()
                .name(create.getName())
                .birthDate(create.getBirthDate())
                .petType(PetType.of(create.getPetType()))
                .owner(owner)
                .build();
    }

    public static PetResDTO.READ entityToDto(Pet pet) {
        return PetResDTO.READ.builder()
                .name(pet.getName())
                .birthDate(pet.getBirthDate())
                .petType(pet.petType)
                .ownerName(pet.getOwner().getFirstName() + pet.getOwner().getLastName())
                .build();
    }

    public void updatePet(PetReqDTO.UPDATE update) {
        this.name = update.getName();
        this.birthDate = update.getBirthDate();
        this.petType = PetType.of(update.getPetType());
    }
}