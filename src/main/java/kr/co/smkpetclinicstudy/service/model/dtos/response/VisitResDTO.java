package kr.co.smkpetclinicstudy.service.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class VisitResDTO {

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class READ {

        private LocalDate visitDate;

        private String description;

        private String petName;
    }
}
