package wishlist.application.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.repository.WishlistRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class IsProductInCustomerWishlistUseCaseTest {

    private WishlistRepository wishlistRepository;
    private IsProductInCustomerWishlistUseCase useCase;

    @BeforeEach
    void setUp() {
        wishlistRepository = Mockito.mock(WishlistRepository.class);
        useCase = new IsProductInCustomerWishlistUseCase(wishlistRepository);
    }

    @ParameterizedTest
    @CsvSource({
            "product1, customer1, true",
            "product1, invalidCustomer, false",
            "invalidProduct, customer1, false"
    })
    void returnsCorrectResultBasedOnProductAndCustomerId(String productId, String customerId, boolean expectedResult) {
        if (expectedResult) {
            when(wishlistRepository.findByProductIdAndCustomerId(productId, customerId)).thenReturn(Optional.of(new Wishlist(customerId)));
        } else {
            when(wishlistRepository.findByProductIdAndCustomerId(productId, customerId)).thenReturn(Optional.empty());
        }

        boolean result = useCase.execute(productId, customerId);

        if (expectedResult) {
            assertTrue(result);
        } else {
            assertFalse(result);
        }
    }
}