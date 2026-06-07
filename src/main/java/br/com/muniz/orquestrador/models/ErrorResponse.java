package br.com.muniz.orquestrador.models;


import java.time.OffsetDateTime;

public record ErrorResponse(
        OffsetDateTime timeStamp,
        Integer status,
        String error,
        String message,
        String path
) { }
