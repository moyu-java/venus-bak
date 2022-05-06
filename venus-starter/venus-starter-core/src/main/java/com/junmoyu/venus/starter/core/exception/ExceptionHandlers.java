package com.junmoyu.venus.starter.core.exception;

import com.junmoyu.venus.starter.core.model.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局统一异常捕获处理
 *
 * @author moyu.jun
 * @date 2022/3/11
 * @see <a href="https://dzone.com/articles/global-exception-handling-with-controlleradvice">global-exception-handling-with-controlleradvice</a>
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    protected Response<String> handleExceptionHandler(final Exception exception) {
        log.error(exception.getMessage(), exception);
        String message;
        if (exception instanceof VenusException) {
            VenusException venusException = (VenusException) exception;
            message = venusException.getMessage();
        } else {
            message = "The system is busy, please try again later";
        }
        return Response.failure(message);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    protected Response<String> handleDuplicateKeyException(final DuplicateKeyException exception) {
        log.error("duplicate key exception ", exception);
        return Response.failure("unique index conflict, please enter again");
    }

    @ExceptionHandler(NullPointerException.class)
    protected Response<String> handleNullPointException(final NullPointerException exception) {
        log.error("null pointer exception ", exception);
        return Response.failure("null pointer exception");
    }

    @ExceptionHandler(ClassNotFoundException.class)
    protected Response<String> handleClassNotFoundException(final ClassNotFoundException e) {
        log.warn("class not found exception", e);
        return Response.failure("class not found exception");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected Response<String> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.warn("http request method not supported", e);
        StringBuilder sb = new StringBuilder();
        sb.append(e.getMethod());
        sb.append(" method is not supported for this request. Supported methods are ");
        Objects.requireNonNull(e.getSupportedHttpMethods()).forEach(t -> sb.append(t).append(" "));
        return Response.failure(sb.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Response<String> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.warn("method argument not valid", e);
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = bindingResult.getFieldErrors().stream().map(f -> f.getField().concat(": ").concat(Optional.ofNullable(f.getDefaultMessage()).orElse(""))).collect(Collectors.joining("| "));
        return Response.failure(String.format("Request error! invalid argument [%s]", errorMsg));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected Response<String> handleMissingServletRequestParameterException(final MissingServletRequestParameterException e) {
        log.warn("missing servlet request parameter", e);
        return Response.failure(String.format("%s parameter is missing", e.getParameterName()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected Response<String> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        log.warn("method argument type mismatch", e);
        return Response.failure(String.format("%s should be of type %s", e.getName(), Objects.requireNonNull(e.getRequiredType()).getName()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected Response<String> handleConstraintViolationException(final ConstraintViolationException e) {
        log.warn("constraint violation exception", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        return Response.failure(violations.stream().map(v -> v.getPropertyPath().toString().concat(": ").concat(v.getMessage())).collect(Collectors.joining("| ")));
    }

    @ExceptionHandler(VenusException.class)
    protected Response<String> handleVenusException(final VenusException exception) {
        log.error("venus exception ", exception);
        return Response.failure(exception.getCode(), exception.getMessage());
    }
}
