package kr.co.smkpetclinicstudy.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class VetResDTO {

    private String firstName;

    private String lastName;
}
