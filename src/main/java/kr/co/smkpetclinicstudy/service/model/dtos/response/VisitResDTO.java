package kr.co.smkpetclinicstudy.service.model.dtos.response;

import lombok.*;

import java.time.LocalDate;

public class VisitResDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ {

        private LocalDate visitDate;

        private String description;

        private String petName;
    }
}
