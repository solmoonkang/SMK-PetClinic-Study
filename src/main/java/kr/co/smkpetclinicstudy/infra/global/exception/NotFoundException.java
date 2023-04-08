package kr.co.smkpetclinicstudy.infra.global.exception;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;

public class NotFoundException extends BusinessException {

    /** super
     *  부모 클래스의 생성자를 호출하는 데 사용된다
     *  BussinessException 클래스에서 상속받은 errorCode 필드는 이미 ErrorCode 객체를 참조하므로
     *  super 키워드를 사용하여 errorCode 필드를 전달하면 된다
     */

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
