package br.com.muniz.orquestrador.exceptions;

public class GenericException extends RuntimeException{
    public GenericException() {
        super("Aconteceu um erro genérico");
    }

    public GenericException(String message) {
        super(message);
    }
}
