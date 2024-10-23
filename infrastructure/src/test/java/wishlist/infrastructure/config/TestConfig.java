package wishlist.infrastructure.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wishlist.application.usecase.AddProductToCustomerWishlistUseCase;
import wishlist.application.usecase.IsProductInCustomerWishlistUseCase;
import wishlist.application.usecase.ListProductsFromCustomerWishlistUseCase;
import wishlist.application.usecase.RemoveProductFromCustomerWishlistUseCase;
import wishlist.infrastructure.controller.WishlistController;

@Configuration
public class TestConfig {

    @Bean
    public AddProductToCustomerWishlistUseCase testAddProductToCustomerWishlistUseCase() {
        return Mockito.mock(AddProductToCustomerWishlistUseCase.class);
    }

    @Bean
    public RemoveProductFromCustomerWishlistUseCase testRemoveProductFromCustomerWishlistUseCase() {
        return Mockito.mock(RemoveProductFromCustomerWishlistUseCase.class);
    }

    @Bean
    public ListProductsFromCustomerWishlistUseCase testListProductsFromCustomerWishlistUseCase() {
        return Mockito.mock(ListProductsFromCustomerWishlistUseCase.class);
    }

    @Bean
    public IsProductInCustomerWishlistUseCase testIsProductInCustomerWishlistUseCase() {
        return Mockito.mock(IsProductInCustomerWishlistUseCase.class);
    }

    @Bean
    public WishlistController wishlistController() {
        return new WishlistController(
                testAddProductToCustomerWishlistUseCase(),
                testIsProductInCustomerWishlistUseCase(),
                testRemoveProductFromCustomerWishlistUseCase(),
                testListProductsFromCustomerWishlistUseCase()
        );
    }
}