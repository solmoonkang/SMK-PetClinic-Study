package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.service.model.request.OwnerRequest;
import kr.co.smkpetclinicstudy.service.model.response.OwnerResponse;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_owners")
@AttributeOverride(     // 하나의 Entity에서 같은 값 타입을 사용하면 Column 명이 중복되므로 Column 명 속성을 재정의
        name = "id",
        column = @Column(name = "owners_id", length = 4))
public class Owners extends BaseEntity {

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "city", length = 80)
    private String city;

    @Column(name = "telephone", length = 20)
    private String telephone;


    @Builder
    public Owners(String firstName,
                  String lastName,
                  String address,
                  String city,
                  String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

    public static Owners of(OwnerRequest ownerRequest) {
        return Owners.builder()
                .firstName(ownerRequest.getFirstName())
                .lastName(ownerRequest.getLastName())
                .address(ownerRequest.getAddress())
                .city(ownerRequest.getCity())
                .telephone(ownerRequest.getTelephone())
                .build();
    }

    public static OwnerResponse of(Owners owners) {
        return OwnerResponse.builder()
                .id(owners.getId())
                .firstName(owners.getFirstName())
                .lastName(owners.getLastName())
                .city(owners.getCity())
                .build();
    }



    public void edit(String firstName,
                     String lastName,
                     String address,
                     String city,
                     String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }
}