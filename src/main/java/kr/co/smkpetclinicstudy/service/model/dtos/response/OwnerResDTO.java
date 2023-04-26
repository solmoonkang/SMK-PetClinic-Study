package kr.co.smkpetclinicstudy.service.model.dtos.response;

import lombok.*;

public class OwnerResDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ {

        private Long ownerId;

        private String firstName;

        private String lastName;

        private String city;
    }
}
