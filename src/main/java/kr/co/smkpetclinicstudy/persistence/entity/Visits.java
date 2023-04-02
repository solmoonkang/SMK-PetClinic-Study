package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.service.model.request.VisitsRequest;
import kr.co.smkpetclinicstudy.service.model.response.VisitsResponse;
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
public class Visits extends BaseEntity {

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pets_id")
    private Pets pets;


    @Builder
    public Visits(LocalDate visitDate,
                  String description,
                  Pets pets) {
        this.visitDate = visitDate;
        this.description = description;
        this.pets = pets;
    }

    public static Visits of(VisitsRequest visitsRequest) {
        return Visits.builder()
                .visitDate(LocalDate.now())
                .description(visitsRequest.getDescription())
                .pets(visitsRequest.getPets())
                .build();
    }

    public static VisitsResponse of(Visits visits) {
        return VisitsResponse.builder()
                .visitDate(LocalDate.now())
                .description(visits.getDescription())
                .pets(visits.getPets())
                .build();
    }
}