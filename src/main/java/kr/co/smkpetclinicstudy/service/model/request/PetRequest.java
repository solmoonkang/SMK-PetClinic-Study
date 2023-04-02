package kr.co.smkpetclinicstudy.service.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import kr.co.smkpetclinicstudy.persistence.entity.Owners;
import kr.co.smkpetclinicstudy.persistence.enums.PetsTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class PetRequest {

    @NotBlank(message = "Please enter your pet name")
    private String name;

    @NotBlank(message = "Please enter your pet birth date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birthDate;

    @NotBlank(message = "Please enter your pets types")
    private PetsTypes petsTypes;

    @NotBlank(message = "Please enter your pet owner id")
    private Owners ownersId;
}
