package kr.co.smkpetclinicstudy.service.model.response;

import kr.co.smkpetclinicstudy.persistence.entity.Owners;
import lombok.Getter;

@Getter
public class OwnerResponse {

    private final Long ownerId;

    private final String firstName;

    private final String lastName;

    private final String city;


    public OwnerResponse(Owners owners) {
        this.ownerId = owners.getId();
        this.firstName = owners.getFirstName();
        this.lastName = owners.getLastName();
        this.city = owners.getCity();
    }
}
