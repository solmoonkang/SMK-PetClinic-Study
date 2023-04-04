package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.service.model.request.VetReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.VetResDTO;
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
        column = @Column(name = "vets_id", length = 4))
public class Vet extends BaseEntity {

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @OneToMany(mappedBy = "vet")
    private List<VetSpecialty> vetSpecialtyList = new ArrayList<>();


    @Builder
    public Vet(String firstName,
               String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Vet of(VetReqDTO vetReqDTO) {
        return Vet.builder()
                .firstName(vetReqDTO.getFirstName())
                .lastName(vetReqDTO.getLastName())
                .build();
    }

    public static VetResDTO of(Vet vet) {
        return VetResDTO.builder()
                .firstName(vet.getFirstName())
                .lastName(vet.getLastName())
                .build();
    }

    public void update(String firstName,
                       String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}