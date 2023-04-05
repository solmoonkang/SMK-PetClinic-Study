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

    public static Owner dtoToEntity(OwnerReqDTO.CREATE create) {
        return Owner.builder()
                .firstName(create.getFirstName())
                .lastName(create.getLastName())
                .address(create.getAddress())
                .city(create.getCity())
                .telephone(create.getTelephone())
                .build();
    }

    public static OwnerResDTO.READ entityToDto(Owner owner) {
        return OwnerResDTO.READ.builder()
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .city(owner.getCity())
                .build();
    }

    public void updateOwner(OwnerReqDTO.UPDATE update) {
        this.firstName = update.getFirstName();
        this.lastName = update.getLastName();
        this.city = update.getCity();
        this.address = update.getAddress();
        this.telephone = update.getTelephone();
    }
}