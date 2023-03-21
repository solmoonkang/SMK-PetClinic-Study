package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_visits")
public class Visits {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visits_id", length = 4)
    private Long visitsId;

    @Column(name = "visits_date")
    private LocalDateTime visitDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "pets_id")
    private Pets pets;


    @Builder
    public Visits(LocalDateTime visitDate,
                  String description,
                  Pets pets) {
        this.visitDate = visitDate;
        this.description = description;
        this.pets = pets;
    }
}