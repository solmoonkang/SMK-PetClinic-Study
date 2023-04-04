package kr.co.smkpetclinicstudy.service.model.response;

import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.enums.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class PetResDTO {

    private Long petsId;

    private String name;

    private LocalDate birthDate;

    private PetType petType;

    private Owner owner;
}
