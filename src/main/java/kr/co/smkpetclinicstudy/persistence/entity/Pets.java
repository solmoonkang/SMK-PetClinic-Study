package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.persistence.enums.PetsTypes;
import kr.co.smkpetclinicstudy.service.model.request.PetsRequest;
import kr.co.smkpetclinicstudy.service.model.response.PetsResponse;
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


    public static Pets of(PetsRequest petsRequest) {
        return Pets.builder()
                .name(petsRequest.getName())
                .birthDate(petsRequest.getBirthDate())
                .petsTypes(petsRequest.getPetsTypes())
                .owners(petsRequest.getOwnersId())
                .build();
    }

    public static PetsResponse of(Pets pets) {
        return PetsResponse.builder()
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