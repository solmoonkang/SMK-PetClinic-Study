package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_visits")
@AttributeOverride(
        name = "id",
        column = @Column(name = "visit_id", length = 4))
public class Visit extends BaseEntity {

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;


    @Builder
    private Visit(LocalDate visitDate,
                 String description,
                 Owner owner,
                 Pet pet) {
        this.visitDate = visitDate;
        this.description = description;
        this.owner = owner;
        this.pet = pet;
    }


    public void updatePetDescription(VisitReqDTO.UPDATE update) {
        this.description = update.getDescription();
    }
}