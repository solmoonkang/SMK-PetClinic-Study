package kr.co.smkpetclinicstudy.service.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OwnerResDTO {

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class READ {

        private String firstName;

        private String lastName;

        private String city;
    }
}
