package wishlist.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wishlist.domain.exception.WishlistNotFoundException;
import wishlist.infrastructure.dto.ErrorMessageDTO;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(WishlistNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> notFound(WishlistNotFoundException wishlistNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessageDTO(wishlistNotFoundException.getMessage()));
    }
}
