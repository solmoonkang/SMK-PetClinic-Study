package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.smkpetclinicstudy.persistence.BaseEntity;
import kr.co.smkpetclinicstudy.service.model.enums.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "tbl_members",
        indexes = @Index(name = "i_members", columnList = "member_id")
)
@AttributeOverride(
        name = "id",
        column = @Column(name = "member_id", length = 4)
)
public class Member extends BaseEntity {

    @Column(name = "member_identity",
            nullable = false,
            unique = true,
            length = 30)
    private String identity;

    @Column(name = "member_password",
            nullable = false)
    private String password;

    @Column(name = "member_name",
            nullable = false)
    private String name;

    @Column(name = "role_type")
    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;


    @Builder
    private Member(String identity,
                   String password,
                   String name,
                   RoleType roleType) {

        this.identity = identity;
        this.password = password;
        this.name = name;
        this.roleType = roleType;
    }
}
