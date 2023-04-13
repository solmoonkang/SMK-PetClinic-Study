package kr.co.smkpetclinicstudy.infra.global.exception;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidInputException extends BusinessException {

    public InvalidInputException(ErrorCode errorCode) {
        super(errorCode);
    }
}
