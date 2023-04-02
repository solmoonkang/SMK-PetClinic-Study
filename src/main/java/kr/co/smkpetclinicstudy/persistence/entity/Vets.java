package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.persistence.enums.VetsSpecialties;
import kr.co.smkpetclinicstudy.service.model.request.VetsRequest;
import kr.co.smkpetclinicstudy.service.model.response.VetsResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_vets")
@AttributeOverride(
        name = "id",
        column = @Column(name = "vets_id", length = 4))
public class Vets extends BaseEntity {

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "vets_specialties")
    @Enumerated(value = EnumType.STRING)
    private VetsSpecialties vetsSpecialties;


    @Builder
    public Vets(String firstName,
                String lastName,
                VetsSpecialties vetsSpecialties) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.vetsSpecialties = vetsSpecialties;
    }

    public static Vets of(VetsRequest vetsRequest) {
        return Vets.builder()
                .firstName(vetsRequest.getFirstName())
                .lastName(vetsRequest.getLastName())
                .vetsSpecialties(vetsRequest.getVetsSpecialties())
                .build();
    }
}