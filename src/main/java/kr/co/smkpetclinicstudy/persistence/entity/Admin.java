package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.service.model.enums.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_admins")
@AttributeOverride(
        name = "id",
        column = @Column(name = "admin_id", length = 4))
public class Admin extends BaseEntity {

    @Column(name = "admin_name")
    private String name;

    @Column(name = "admin_identity")
    private String identity;

    @Column(name = "admin_password")
    private String password;

    @Column(name = "role_type")
    private RoleType roleType;


    @Builder
    private Admin(String name,
                  String identity,
                  String password,
                  RoleType roleType) {

        this.name = name;
        this.identity = identity;
        this.password = password;
        this.roleType = roleType;
    }
}
