package wishlist.application.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wishlist.domain.entity.Product;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.exception.WishlistNotFoundException;
import wishlist.domain.repository.WishlistRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ListProductsFromCustomerWishlistUseCaseTest {

    private WishlistRepository wishlistRepository;
    private ListProductsFromCustomerWishlistUseCase useCase;

    @BeforeEach
    void setUp() {
        wishlistRepository = Mockito.mock(WishlistRepository.class);
        useCase = new ListProductsFromCustomerWishlistUseCase(wishlistRepository);
    }

    @Test
    void returnsProductsWhenWishlistExists() {
        String customerId = "customer1";
        Wishlist wishlist = new Wishlist(customerId);
        Product product1 = new Product("1", "Product 1");
        Product product2 = new Product("2", "Product 2");
        wishlist.addProduct(product1);
        wishlist.addProduct(product2);
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));

        Set<Product> result = useCase.execute(customerId);

        assertEquals(2, result.size());
        assertTrue(result.contains(product1));
        assertTrue(result.contains(product2));
    }

    @Test
    void throwsExceptionWhenWishlistNotFound() {
        String customerId = "customer1";
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());

        assertThrows(WishlistNotFoundException.class, () -> useCase.execute(customerId));
    }

    @Test
    void returnsEmptySetWhenWishlistHasNoProducts() {
        String customerId = "customer1";
        Wishlist wishlist = new Wishlist(customerId);
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));

        Set<Product> result = useCase.execute(customerId);

        assertTrue(result.isEmpty());
    }
}