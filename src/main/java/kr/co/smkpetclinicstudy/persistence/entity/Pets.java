package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.enums.PetsTypes;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_pets")
public class Pets {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pets_id", length = 4)
    private Long petsId;

    @Column(length = 30)
    private String name;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "pets_types")
    private PetsTypes petsTypes;

    @ManyToOne
    @JoinColumn(name = "owners_id")
    private Owners owners;


    @Builder
    public Pets(String name,
                LocalDateTime birthDate,
                PetsTypes petsTypes,
                Owners owners) {
        this.name = name;
        this.birthDate = birthDate;
        this.petsTypes = petsTypes;
        this.owners = owners;
    }
}