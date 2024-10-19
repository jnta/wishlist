package br.com.jonataalbuquerque.exception;

public class ProductLimitExceededException extends RuntimeException {
    public ProductLimitExceededException(String message) {
        super(message);
    }
}
