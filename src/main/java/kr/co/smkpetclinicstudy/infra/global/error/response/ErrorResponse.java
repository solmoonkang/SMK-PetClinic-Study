package kr.co.smkpetclinicstudy.infra.global.error.response;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int status;

    private HttpStatus httpStatus;

    private String message;


    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .httpStatus(errorCode.getHttpStatus())
                        .message(errorCode.getMessage())
                        .build());
    }
}
