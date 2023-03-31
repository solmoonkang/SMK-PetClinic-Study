package kr.co.smkpetclinicstudy.service.model.response;

import kr.co.smkpetclinicstudy.persistence.entity.Owners;
import kr.co.smkpetclinicstudy.persistence.enums.PetsTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class PetResponse {

    private Long petsId;

    private String name;

    private LocalDate birthDate;

    private PetsTypes petsTypes;

    private Owners owners;
}
