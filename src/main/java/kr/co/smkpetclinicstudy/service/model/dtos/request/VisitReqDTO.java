package kr.co.smkpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

public class VisitReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        private LocalDate visitDate;

        private String description;

        @NotNull(message = "Please enter your pet name")
        private Long petId;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UPDATE {

        @NotNull(message = "Please enter your visit")
        private Long visitId;

        private String description;
    }
}
