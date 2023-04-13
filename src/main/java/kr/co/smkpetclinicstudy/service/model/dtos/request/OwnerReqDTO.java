package kr.co.smkpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OwnerReqDTO {

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
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
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class UPDATE {

        @NotNull(message = "Please enter your owner")
        private Long ownerId;

        @NotBlank()
        private String firstName;

        private String lastName;

        private String address;

        private String city;

        private String telephone;
    }
}
