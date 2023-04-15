package kr.co.smkpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class OwnerReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        @NotBlank(message = "Please enter your first name")
        private String firstName;

        @NotBlank(message = "Please enter your last name")
        private String lastName;

        @NotBlank(message = "Please enter your detail address")
        private String address;

        @NotBlank(message = "Please enter your city name")
        private String city;

        @NotBlank(message = "Please enter your telephone, 010-xxxx-xxxx")
        private String telephone;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UPDATE {

        @NotNull(message = "Please enter your owner")
        private Long ownerId;

        private String firstName;

        private String lastName;

        private String address;

        private String city;

        private String telephone;
    }
}
