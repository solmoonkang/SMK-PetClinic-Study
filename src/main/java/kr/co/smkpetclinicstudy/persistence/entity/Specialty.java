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

    @Column(name = "name", length = 80)
    private String name;


    @Builder
    public Specialty(String name) {
        this.name = name;
    }

    public static Specialty paramToEntity(String name) {
        return Specialty.builder()
                .name(name)
                .build();
    }
}
