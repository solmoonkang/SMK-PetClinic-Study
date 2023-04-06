package kr.co.smkpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class VisitReqDTO {

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class CREATE {

        private LocalDate visitDate;

        private String description;

        @NotNull(message = "Please enter your pet name")
        private Long petId;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class UPDATE {

        @NotNull(message = "Please enter your visit")
        private Long visitId;

        private String description;
    }
}
