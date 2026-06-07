package br.com.muniz.orquestrador.utils;

import br.com.muniz.orquestrador.models.ApiResponse;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

public class ResponseUtil {

    public static <T> ApiResponse<T> success(T data, String message, String path, Integer statusCode) {
        ApiResponse<T> response = new ApiResponse<>();

        response.setData(data);
        response.setMessage(message);
        response.setPath(path);
        response.setSuccess(Boolean.TRUE);
        response.setError(null);
        response.setTimestamp(OffsetDateTime.now());
        response.setStatusCode(statusCode);

        return response;
    }

    public static <T> ApiResponse<T> error(List<String> error, String message, String path, Integer statusCode) {
        ApiResponse<T> response = new ApiResponse<>();

        response.setData(null);
        response.setMessage(message);
        response.setPath(path);
        response.setSuccess(Boolean.FALSE);
        response.setError(error);
        response.setTimestamp(OffsetDateTime.now());
        response.setStatusCode(statusCode);

        return response;
    }

    public static <T> ApiResponse<T> error(String error, String message, String path, Integer statusCode) {
        return error(Collections.singletonList(error), message, path, statusCode);
    }
}
