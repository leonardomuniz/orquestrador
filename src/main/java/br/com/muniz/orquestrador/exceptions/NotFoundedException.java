package br.com.muniz.orquestrador.exceptions;

public class NotFoundedException extends RuntimeException{

    public NotFoundedException(String message) {
        super(message);
    }

}
