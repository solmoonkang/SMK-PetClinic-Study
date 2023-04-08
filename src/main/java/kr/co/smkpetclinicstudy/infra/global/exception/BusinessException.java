package kr.co.smkpetclinicstudy.infra.global.exception;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    /** final
     *  한 번 할당된 값을 변경할 수 없도록 만들어준다
     *  final 키워드를 사용해 선언된 변수는 수정이 불가능하며, 초기화 이후에 값이 변경되지 않도록 보장한다
     *  따라서 final 키워드를 사용해 ErrorCode를 변경할 수 없도록 하여, 해당 예외에 대한 정보를 안전하게 전달할 수 있다
    */


    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
