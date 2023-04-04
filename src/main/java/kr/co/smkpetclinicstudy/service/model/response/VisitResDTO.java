package kr.co.smkpetclinicstudy.service.model.response;

import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class VisitResDTO {

    private LocalDate visitDate;

    private String description;

    private Pet pet;
}
