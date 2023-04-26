package kr.co.smkpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

public class PetReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        @NotBlank(message = "Please enter your pet name")
        private String name;

        private LocalDate birthDate;

        @NotBlank(message = "Please enter your pet type")
        private String petType;

        @NotNull(message = "Please enter your owner id")
        private Long ownerId;

        @NotNull(message = "Please enter your vet id")
        private Long vetId;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UPDATE {

        @NotNull(message = "Please enter your pet id")
        private Long petId;

        private String name;

        private LocalDate birthDate;

        private String petType;
    }
}
