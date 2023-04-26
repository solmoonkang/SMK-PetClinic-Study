package kr.co.smkpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

public class VetReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        @NotBlank(message = "Please enter your first name")
        private String firstName;

        @NotBlank(message = "Please enter your last name")
        private String lastName;

        private List<String> vetSpecialtiesName;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UPDATE {

        @NotNull(message = "Please enter your vet")
        private Long vetId;

        private String firstName;

        private String lastName;

        private List<String> vetSpecialtiesName;
    }
}
