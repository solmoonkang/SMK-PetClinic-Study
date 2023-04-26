package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_specialties")
@AttributeOverride(
        name = "id",
        column = @Column(name = "specialty_id", length = 4))
public class Specialty extends BaseEntity {

    @Column(name = "specialty_name", length = 80)
    private String specialtyName;

    @OneToMany(
            mappedBy = "specialty",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<VetSpecialty> vetSpecialties = new ArrayList<>();


    @Builder
    private Specialty(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}
