package kr.co.smkpetclinicstudy.service.model.dtos.response;

import lombok.*;

import java.util.List;

public class VetResDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ {

        private String firstName;

        private String lastName;

        private List<String> vetSpecialtiesName;
    }
}
