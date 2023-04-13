package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VetResDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_vets")
@AttributeOverride(
        name = "id",
        column = @Column(name = "vet_id", length = 4))
public class Vet extends BaseEntity {

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @OneToMany(
            mappedBy = "vet",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<VetSpecialty> vetSpecialties = new ArrayList<>();

    @Builder
    public Vet(String firstName,
               String lastName,
               List<VetSpecialty> vetSpecialties) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.vetSpecialties = vetSpecialties;
    }


    public void updateVetSpecialties(List<VetSpecialty> vetSpecialties) {
        this.vetSpecialties = vetSpecialties;
    }
}