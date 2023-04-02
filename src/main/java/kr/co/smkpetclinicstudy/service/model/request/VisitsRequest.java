package kr.co.smkpetclinicstudy.service.model.request;

import jakarta.validation.constraints.NotBlank;
import kr.co.smkpetclinicstudy.persistence.entity.Pets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class VisitsRequest {

    private LocalDate visitDate;

    private String description;

    @NotBlank(message = "Please enter your pets name")
    private Pets pets;
}
