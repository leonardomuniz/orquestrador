package br.com.muniz.orquestrador.exceptions;

import br.com.muniz.orquestrador.models.ApiResponse;
import br.com.muniz.orquestrador.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundedException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundException(NotFoundedException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiResponse<Object> response = ResponseUtil.error(
                exception.getMessage(),
                "Not founded.",
                request.getRequestURI(),
                status.value()
        );

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiResponse<Object> response = ResponseUtil.error(
                "Internal Server Error.",
                "Unexpected Error. Try Again latter.",
                request.getRequestURI(),
                status.value()
        );

        return ResponseEntity.status(status).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        String path = request.getDescription(false).replace("uri=", "");

        ApiResponse<Object> response = ResponseUtil.error(
                errors,
                "Validation Error in Submitted Data",
                path,
                status.value()
        );

        return ResponseEntity.status(status).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String path = request.getDescription(false).replace("uri=", "");

        ApiResponse<Object> response = ResponseUtil.error(
                "O formato do JSON enviado é inválido ou contém tipos de dados incorretos.",
                "Malformed JSON request",
                path,
                status.value()
        );

        return ResponseEntity.status(status).body(response);
    }
}
