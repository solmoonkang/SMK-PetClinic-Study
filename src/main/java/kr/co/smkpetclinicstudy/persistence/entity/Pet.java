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
        column = @Column(name = "pets_id", length = 4))
public class Pet extends BaseEntity {

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "pets_types")
    @Enumerated(value = EnumType.STRING)    // enum 값을 index 값이 아닌 text 값 그대로 저장(즉, DB에 enum 값이 그대로 저장)
    private PetType petType;

    @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩
    @JoinColumn(name = "owners_id")
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


    public static Pet of(PetReqDTO petReqDto) {
        return Pet.builder()
                .name(petReqDto.getName())
                .birthDate(petReqDto.getBirthDate())
                .petsTypes(petReqDto.getPetType())
                .owners(petReqDto.getOwnerId())
                .build();
    }

    public static PetResDTO of(Pet pet) {
        return PetResDTO.builder()
                .petsId(pet.getId())
                .name(pet.getName())
                .birthDate(pet.getBirthDate())
                .petType(pet.getPetType())
                .owner(pet.getOwner())
                .build();
    }

    public void update(String name,
                       Owner owner) {
        this.name = name;
        this.owner = owner;
    }
}