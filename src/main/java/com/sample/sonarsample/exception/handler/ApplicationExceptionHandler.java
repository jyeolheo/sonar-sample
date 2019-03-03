package com.sample.sonarsample.exception.handler;

import com.sample.sonarsample.exception.ApplicationException;
import com.sample.sonarsample.model.message.ResultBody;
import com.sample.sonarsample.model.type.ErrorType;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice(basePackages = {"com.sample.sonarsample"})
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(Exception ex, WebRequest request) {


        return new ResponseEntity<>(new ResultBody(ErrorType.EC_UNKNOWN), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value={MethodArgumentNotValidException.class,
                             HttpMessageNotReadableException.class,
                             MissingServletRequestParameterException.class})
    public ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest request) {

        if (ex instanceof MethodArgumentNotValidException) {
            /*val objectError = Objects.requireNonNull(((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().get(0));
            log.error(((FieldError) objectError).getField()+((FieldError) objectError).getRejectedValue()+objectError.getDefaultMessage());*/
            return new ResponseEntity<>(new ResultBody(ErrorType.EC_INVALID_ARGUMENT), HttpStatus.OK);
        } else if (ex instanceof MissingServletRequestParameterException) {
            return new ResponseEntity<>(new ResultBody(ErrorType.EC_MISSING_REQUEST_PARAMETER), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResultBody(ErrorType.EC_INVALID_JSON_FORMAT), HttpStatus.OK);
        }

    }

    @ExceptionHandler(value={FeignException.class})
    public ResponseEntity<Object> handleFeignException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ResultBody(ErrorType.EC_INTERACTION), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptionGlobally(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ResultBody(ErrorType.EC_UNKNOWN), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
