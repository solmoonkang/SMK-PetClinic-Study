package kr.co.smkpetclinicstudy.service.model.dtos.response;

import kr.co.smkpetclinicstudy.service.model.enums.PetType;
import lombok.*;

import java.time.LocalDate;

public class PetResDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ {

        private String name;

        private LocalDate birthDate;

        private PetType petType;

        private String ownerName;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ_DETAIL {

        private Long petId;

        private String name;

        private LocalDate birthDate;

        private PetType petType;

        private String ownerFirstName;

        private String ownerLastName;

        private String ownerAddress;

        private String ownerTelephone;
    }
}
