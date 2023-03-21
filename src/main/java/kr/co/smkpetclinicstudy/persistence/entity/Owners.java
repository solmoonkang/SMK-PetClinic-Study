package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_owners")
public class Owners {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owners_id", length = 4)
    private Long ownersId;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    private String address;

    @Column(length = 80)
    private String city;

    @Column(length = 20)
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
}