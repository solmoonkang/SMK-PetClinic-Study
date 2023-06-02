package kr.co.smkpetclinicstudy.service.model.enums;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RoleType {

    ROLE_USER("사용자"),

    ROLE_ADMIN("관리자");


    String roleType;

    public static RoleType of(String roleType){
        return Arrays.stream(RoleType.values())
                .filter(type -> type.toString().equalsIgnoreCase(roleType))
                .findAny().orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER));
    }
}