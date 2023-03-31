package kr.co.smkpetclinicstudy.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OwnerResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String city;
}
