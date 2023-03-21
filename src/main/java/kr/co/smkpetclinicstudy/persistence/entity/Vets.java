package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.enums.VetsSpecialties;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_vets")
public class Vets {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vets_id", length = 4)
    private Long vetsId;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "vets")
    private VetsSpecialties vetsSpecialties;


    @Builder
    public Vets(String firstName,
                String lastName,
                VetsSpecialties vetsSpecialties) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.vetsSpecialties = vetsSpecialties;
    }
}