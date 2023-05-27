package kr.co.smkpetclinicstudy.infra.handler;

import kr.co.smkpetclinicstudy.infra.global.error.response.ErrorResponse;
import kr.co.smkpetclinicstudy.infra.global.exception.DuplicatedException;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {

        log.error("handleNotFoundException throws NotFoundException : {}", e.getErrorCode());

        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedException(DuplicatedException e) {

        log.error("handleDuplicatedException throws DuplicatedException : {}", e.getErrorCode());

        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
