package wishlist.application.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wishlist.domain.entity.Product;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.exception.ProductLimitExceededException;
import wishlist.domain.repository.WishlistRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddProductToCustomerWishlistUseCaseTest {

    private WishlistRepository wishlistRepository;
    private AddProductToCustomerWishlistUseCase useCase;

    @BeforeEach
    void setUp() {
        wishlistRepository = Mockito.mock(WishlistRepository.class);
        useCase = new AddProductToCustomerWishlistUseCase(wishlistRepository);
    }

    @Test
    void addsProductToExistingWishlist() {
        Product product = new Product("1", "Product 1");
        String customerId = "customer1";
        Wishlist existingWishlist = new Wishlist(customerId);
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(existingWishlist));
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(existingWishlist);

        Wishlist result = useCase.execute(product, customerId);

        assertEquals(1, result.getProducts().size());
        assertTrue(result.getProducts().contains(product));
        verify(wishlistRepository).save(existingWishlist);
    }

    @Test
    void createsNewWishlistIfNoneExists() {
        Product product = new Product("1", "Product 1");
        String customerId = "customer1";
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());
        Wishlist newWishlist = new Wishlist(customerId);
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(newWishlist);

        Wishlist result = useCase.execute(product, customerId);

        assertEquals(newWishlist, result);
        verify(wishlistRepository).save(any(Wishlist.class));
    }

    @Test
    void addsMultipleProductsToWishlist() {
        Product product1 = new Product("1", "Product 1");
        Product product2 = new Product("2", "Product 2");
        String customerId = "customer1";
        Wishlist existingWishlist = new Wishlist(customerId);
        existingWishlist.addProduct(product1);
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(existingWishlist));
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(existingWishlist);

        Wishlist result = useCase.execute(product2, customerId);

        assertEquals(2, result.getProducts().size());
        assertTrue(result.getProducts().contains(product1));
        assertTrue(result.getProducts().contains(product2));
        verify(wishlistRepository).save(existingWishlist);
    }

    @Test
    void throwsExceptionWhenAddingProductToFullWishlist() {
        String customerId = "customer1";
        Wishlist fullWishlist = new Wishlist(customerId);
        for (int i = 0; i < 20; i++) {
            fullWishlist.addProduct(new Product(String.valueOf(i), "Product " + i));
        }
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(fullWishlist));

        Product newProduct = new Product("21", "Product 21");

        assertThrows(ProductLimitExceededException.class, () -> useCase.execute(newProduct, customerId));
    }

    @Test
    void doesNotThrowExceptionWhenAddingProductToNonFullWishlist() {
        String customerId = "customer1";
        Wishlist nonFullWishlist = new Wishlist(customerId);
        for (int i = 0; i < 19; i++) {
            nonFullWishlist.addProduct(new Product(String.valueOf(i), "Product " + i));
        }
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(nonFullWishlist));

        Product newProduct = new Product("20", "Product 20");

        assertDoesNotThrow(() -> useCase.execute(newProduct, customerId));
    }
}