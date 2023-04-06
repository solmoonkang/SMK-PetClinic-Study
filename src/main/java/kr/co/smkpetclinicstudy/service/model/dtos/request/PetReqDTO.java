package kr.co.smkpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class PetReqDTO {

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class CREATE {

        @NotBlank(message = "Please enter your pet name")
        private String name;

        private LocalDate birthDate;

        @NotBlank(message = "Please enter your pet type")
        private String petType;

        @NotBlank(message = "Please enter your owner")
        private Long ownerId;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class UPDATE {

        @NotNull(message = "Please enter your pet")
        private Long petId;

        private String name;

        private LocalDate birthDate;

        private String petType;

    }
}
