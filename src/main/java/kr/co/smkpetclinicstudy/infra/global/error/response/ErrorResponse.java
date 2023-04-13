package kr.co.smkpetclinicstudy.infra.global.error.response;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int status;

    private String code;

    private String message;


    public static ErrorResponse of(ErrorCode errorCode) {

        return ErrorResponse.builder()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }
}
