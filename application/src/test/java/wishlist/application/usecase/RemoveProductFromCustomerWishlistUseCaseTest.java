package wishlist.application.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.exception.WishlistNotFoundException;
import wishlist.domain.repository.WishlistRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RemoveProductFromCustomerWishlistUseCaseTest {

    private WishlistRepository wishlistRepository;
    private RemoveProductFromCustomerWishlistUseCase useCase;

    @BeforeEach
    void setUp() {
        wishlistRepository = Mockito.mock(WishlistRepository.class);
        useCase = new RemoveProductFromCustomerWishlistUseCase(wishlistRepository);
    }

    @Test
    void removesProductWhenWishlistExists() {
        String productId = "product1";
        String customerId = "customer1";
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(new Wishlist(customerId)));

        useCase.execute(productId, customerId);

        verify(wishlistRepository).deleteProductFromCustomerWishlist(productId, customerId);
    }

    @Test
    void throwsExceptionWhenWishlistNotFound() {
        String productId = "product1";
        String customerId = "customer1";
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());

        assertThrows(WishlistNotFoundException.class, () -> useCase.execute(productId, customerId));
    }
}