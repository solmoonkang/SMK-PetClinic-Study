package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.persistence.enums.PetsTypes;
import kr.co.smkpetclinicstudy.service.model.request.PetRequest;
import kr.co.smkpetclinicstudy.service.model.response.PetResponse;
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
public class Pets extends BaseEntity {

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "pets_types")
    @Enumerated(value = EnumType.STRING)    // enum 값을 index 값이 아닌 text 값 그대로 저장(즉, DB에 enum 값이 그대로 저장)
    private PetsTypes petsTypes;

    @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩
    @JoinColumn(name = "owners_id")
    private Owners owners;


    @Builder
    public Pets(String name,
                LocalDate birthDate,
                PetsTypes petsTypes,
                Owners owners) {
        this.name = name;
        this.birthDate = birthDate;
        this.petsTypes = petsTypes;
        this.owners = owners;
    }


    public static Pets of(PetRequest petRequest) {
        return Pets.builder()
                .name(petRequest.getName())
                .birthDate(petRequest.getBirthDate())
                .petsTypes(petRequest.getPetsTypes())
                .owners(petRequest.getOwnersId())
                .build();
    }

    public static PetResponse of(Pets pets) {
        return PetResponse.builder()
                .petsId(pets.getId())
                .name(pets.getName())
                .birthDate(pets.getBirthDate())
                .petsTypes(pets.getPetsTypes())
                .owners(pets.getOwners())
                .build();
    }

    public void update(String name,
                       Owners owners) {
        this.name = name;
        this.owners = owners;
    }
}