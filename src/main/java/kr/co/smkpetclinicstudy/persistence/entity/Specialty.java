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
@Table(name = "tbl_specialties")
@AttributeOverride(
        name = "id",
        column = @Column(name = "specialty_id", length = 4))
public class Specialty extends BaseEntity {

    @Column(name = "name", length = 80)
    private String specialtyName;


    @Builder
    private Specialty(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}
