package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.service.model.request.OwnerReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.OwnerResDTO;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_owners")
@AttributeOverride(     // 하나의 Entity에서 같은 값 타입을 사용하면 Column 명이 중복되므로 Column 명 속성을 재정의
        name = "id",
        column = @Column(name = "owner_id", length = 4))
public class Owner extends BaseEntity {

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
    public Owner(String firstName,
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

    public static Owner of(OwnerReqDTO ownerReqDto) {
        return Owner.builder()
                .firstName(ownerReqDto.getFirstName())
                .lastName(ownerReqDto.getLastName())
                .address(ownerReqDto.getAddress())
                .city(ownerReqDto.getCity())
                .telephone(ownerReqDto.getTelephone())
                .build();
    }

    public static OwnerResDTO of(Owner owner) {
        return OwnerResDTO.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .city(owner.getCity())
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