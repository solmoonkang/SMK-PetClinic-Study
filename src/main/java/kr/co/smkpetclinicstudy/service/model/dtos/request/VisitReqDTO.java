package kr.co.smkpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import kr.co.smkpetclinicstudy.persistence.entity.Pet;
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

        private String petName;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class UPDATE {

        private Long petId;

        private String description;
    }
}
