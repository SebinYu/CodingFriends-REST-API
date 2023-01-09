package net.skhu.codingFriends.advice;

import lombok.extern.slf4j.Slf4j;
import net.skhu.codingFriends.exception.studygroup.SelfOnlyDeletableException;
import net.skhu.codingFriends.exception.studygroup.SelfOnlyModifiableException;
import net.skhu.codingFriends.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    // 500 에러
    @ExceptionHandler(IllegalArgumentException.class) // 지정한 예외가 발생하면 해당 메소드 실행
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 각 예외마다 상태 코드 지정
    public Response illegalArgumentExceptionAdvice(IllegalArgumentException e) {
        log.info("e = {}", e.getMessage());
        return Response.failure(500, e.getMessage().toString());
    }

    // 400 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) { // 2
        return Response.failure(400, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    // 400 에러
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response bindException(BindException e) {
        return Response.failure(400, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    // 404 응답
    // 본인 게시물만 수정가능
    @ExceptionHandler(SelfOnlyModifiableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response selfOnlyModifiableException() {
        return Response.failure(404, "본인 게시물만 수정할 수 있습니다.");
    }

    // 404 응답
    // 본인 게시물만 삭제가능
    @ExceptionHandler(SelfOnlyDeletableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response selfOnlyDeletableException() {
        return Response.failure(404, "본인 게시물만 삭제할 수 있습니다.");
    }



}