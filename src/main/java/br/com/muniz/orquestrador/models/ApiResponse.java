package br.com.muniz.orquestrador.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiResponse<T>{
    private boolean success;
    private String message;
    private T data;
    private List<String> error;
    private OffsetDateTime timestamp;
    private String path;
    private Integer statusCode;
}
