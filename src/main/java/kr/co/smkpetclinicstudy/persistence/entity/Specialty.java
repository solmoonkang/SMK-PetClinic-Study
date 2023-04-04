package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import lombok.AccessLevel;
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
        column = @Column(name = "specialties_id", length = 4))
public class Specialty extends BaseEntity {

    @OneToMany(mappedBy = "specialty")
    private List<VetSpecialty> vetSpecialtyList = new ArrayList<>();
}
