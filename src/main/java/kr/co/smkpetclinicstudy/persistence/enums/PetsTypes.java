package kr.co.smkpetclinicstudy.persistence.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PetsTypes {

    MALTESE("maltese"),
    SHIHTZU("shihtzu"),
    BICHON("bichon"),
    POODLE("poodle");

    @JsonValue      // 직렬화
    private final String value;

    PetsTypes(String value) {
        this.value = value;
    }
}
