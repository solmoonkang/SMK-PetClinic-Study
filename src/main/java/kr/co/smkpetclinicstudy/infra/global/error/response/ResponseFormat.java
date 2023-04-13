package kr.co.smkpetclinicstudy.infra.global.error.response;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseFormat<T> {    // 제네릭 타입 : 타입을 파라미터로 가지는 class 와 interface 를 말한다

    private int status;

    private String message;

    private boolean success;

    /** Generic
     *  응답 데이터의 타입이 다양할 수 있기 때문에 사용한다
     *  EX) 클라이언트가 요청하는 API 의 응답 데이터가 문자열, JSON 객체 또는 사용자가 정의한 복잡한 객체일 수도 있으므로 다양한 타입의 데이터를 처리한다
     *  장점 : 코드의 재사용성이 높아지고 유연성이 증가한다 또한 컴파일 시점에 타입 안전성을 보장할 수 있어 런타임 오류를 줄일 수 있다
     */

    private T data;


    public static <T> ResponseFormat<T> successMessage(ErrorCode errorCode ,String message) {
        return ResponseFormat.<T>builder()
                .status(errorCode.getStatus())
                .message(message)
                .success(true)
                .data(null)
                .build();
    }

    public static <T> ResponseFormat<T> successData(ErrorCode errorCode, T data) {
        return ResponseFormat.<T>builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .success(true)
                .data(data)
                .build();

    }

    public static <T> ResponseFormat<T> fail(ErrorCode errorCode) {
        return ResponseFormat.<T>builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .success(false)
                .data(null)
                .build();
    }
}
