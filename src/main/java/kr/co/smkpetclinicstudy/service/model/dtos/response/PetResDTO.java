package kr.co.smkpetclinicstudy.service.model.dtos.response;

import kr.co.smkpetclinicstudy.service.model.enums.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class PetResDTO {

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class READ {

        private String name;

        private LocalDate birthDate;

        private PetType petType;

        private String ownerName;
    }
}
