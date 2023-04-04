package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.service.model.request.VisitReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.VisitResDTO;
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
        column = @Column(name = "visits_id", length = 4))
public class Visit extends BaseEntity {

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pets_id")
    private Pet pet;


    @Builder
    public Visit(LocalDate visitDate,
                 String description,
                 Pet pet) {
        this.visitDate = visitDate;
        this.description = description;
        this.pet = pet;
    }

    public static Visit of(VisitReqDTO visitReqDTO) {
        return Visit.builder()
                .visitDate(LocalDate.now())
                .description(visitReqDTO.getDescription())
                .pets(visitReqDTO.getPet())
                .build();
    }

    public static VisitResDTO of(Visit visit) {
        return VisitResDTO.builder()
                .visitDate(LocalDate.now())
                .description(visit.getDescription())
                .pet(visit.getPet())
                .build();
    }

    public void update(String description) {
        this.description = description;
    }
}