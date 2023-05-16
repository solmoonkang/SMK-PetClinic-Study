package kr.co.smkpetclinicstudy.infra.handler;

import kr.co.smkpetclinicstudy.infra.global.error.response.ErrorResponse;
import kr.co.smkpetclinicstudy.infra.global.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {

        log.error("handleBusinessException throws BusinessException : {}", e.getErrorCode());

        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
