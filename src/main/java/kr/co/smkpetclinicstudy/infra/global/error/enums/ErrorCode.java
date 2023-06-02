package kr.co.smkpetclinicstudy.infra.global.error.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Common
    SUCCESS_EXECUTE(200, HttpStatus.OK, "성공적으로 실행되었습니다"),
    SUCCESS_CREATED(201, HttpStatus.CREATED, "성공적으로 실행되었고, 새 리소스가 생성되었습니다"),

    FAIL(203, HttpStatus.BAD_REQUEST, "실행에 실패했습니다"),
    FAIL_INVALID_VALUE(400, HttpStatus.BAD_REQUEST, "실행에 실패했고, 이유는 필수 항목을 입력하지 않았습니다"),
    FAIL_BAD_REQUEST(400, HttpStatus.BAD_REQUEST, "실행에 실패했고, 이유는 형식에 맞지 않는 값을 입력하였습니다"),

    // Member
    NOT_FOUND_MEMBER(404, HttpStatus.NOT_FOUND, "해당 사용자 정보를 찾지 못하였습니다"),

    DUPLICATED_MEMBER_ID(400, HttpStatus.BAD_REQUEST, "사용자 ID가 중복되었습니다"),

    // Owner
    DUPLICATED_OWNER_ID(400, HttpStatus.BAD_REQUEST, "소유자 ID가 중복되었습니다"),
    DUPLICATED_OWNER_PHONE(400, HttpStatus.BAD_REQUEST, "소유자 핸드폰 번호가 중복되었습니다"),

    NOT_FOUND_OWNER(404, HttpStatus.NOT_FOUND, "해당 소유자 정보를 찾지 못하였습니다"),

    // Pet
    DUPLICATED_PET_ID(400, HttpStatus.BAD_REQUEST, "반려동물의 ID가 중복되었습니다"),

    NOT_FOUND_PET(404, HttpStatus.NOT_FOUND, "해당 반려동물 정보를 찾지 못하였습니다"),

    // Vet
    NOT_FOUND_VET(404, HttpStatus.NOT_FOUND, "해당 수의사 정보를 찾지 못하였습니다"),

    // Visit
    NOT_FOUND_VISIT(404, HttpStatus.NOT_FOUND, "해당 방문자 정보를 찾지 못했습니다");



    private final int status;

    private final HttpStatus httpStatus;

    private final String message;


    ErrorCode(int status,
              HttpStatus httpStatus,
              String message) {

        this.status = status;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
