package wishlist.domain.exception;

public class ProductLimitExceededException extends RuntimeException {
    public ProductLimitExceededException(String message) {
        super(message);
    }
}
