package kr.co.smkpetclinicstudy.service.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignUpRequest {

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
