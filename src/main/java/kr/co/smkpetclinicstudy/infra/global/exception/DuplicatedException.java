package kr.co.smkpetclinicstudy.infra.global.exception;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;

public class DuplicatedException extends BusinessException {

    public DuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
