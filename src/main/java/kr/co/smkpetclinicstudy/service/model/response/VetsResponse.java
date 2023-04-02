package kr.co.smkpetclinicstudy.service.model.response;

import kr.co.smkpetclinicstudy.persistence.enums.VetsSpecialties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class VetsResponse {

    private String firstName;

    private String lastName;

    private VetsSpecialties vetsSpecialties;
}
