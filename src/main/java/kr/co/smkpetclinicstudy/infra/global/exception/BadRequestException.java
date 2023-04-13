package kr.co.smkpetclinicstudy.infra.global.exception;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;

public class BadRequestException extends BusinessException {

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
