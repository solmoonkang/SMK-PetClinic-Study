package kr.co.smkpetclinicstudy.service.model.request;

import jakarta.validation.constraints.NotBlank;
import kr.co.smkpetclinicstudy.persistence.enums.VetsSpecialties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VetRequest {

    @NotBlank(message = "Please enter your first name")
    private String firstName;

    @NotBlank(message = "Please enter your last name")
    private String lastName;

    @NotBlank(message = "Please enter your specialties")
    private VetsSpecialties vetsSpecialties;
}
