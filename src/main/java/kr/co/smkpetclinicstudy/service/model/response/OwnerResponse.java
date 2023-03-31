package kr.co.smkpetclinicstudy.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OwnerResponse {

    private final String firstName;

    private final String lastName;

    private final String city;
}
