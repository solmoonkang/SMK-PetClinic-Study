package kr.co.smkpetclinicstudy.service.model.enums;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PetType {

    DOG("강아지"),

    CAT("고양이"),

    BIRD("새"),

    REPTILE("파충류"),

    OTHER("기타");


    String petType;

    public static PetType of(String petType){
        return Arrays.stream(PetType.values())
                .filter(type -> type.toString().equalsIgnoreCase(petType))
                .findAny().orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_PET));
    }
}
