package wishlist.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wishlist.application.usecase.AddProductToCustomerWishlistUseCase;
import wishlist.application.usecase.IsProductInCustomerWishlistUseCase;
import wishlist.application.usecase.ListProductsFromCustomerWishlistUseCase;
import wishlist.application.usecase.RemoveProductFromCustomerWishlistUseCase;
import wishlist.infrastructure.adapter.WishlistMongoRepositoryAdapter;

@Configuration
public class BeanConfiguration {

    @Bean
    public AddProductToCustomerWishlistUseCase addProductToCustomerWishlistUseCase(WishlistMongoRepositoryAdapter wishlistMongoRepositoryAdapter) {
        return new AddProductToCustomerWishlistUseCase(wishlistMongoRepositoryAdapter);
    }

    @Bean
    public IsProductInCustomerWishlistUseCase isProductInCustomerWishlistUseCase(WishlistMongoRepositoryAdapter wishlistMongoRepositoryAdapter) {
        return new IsProductInCustomerWishlistUseCase(wishlistMongoRepositoryAdapter);
    }

    @Bean
    public ListProductsFromCustomerWishlistUseCase listProductsFromCustomerWishlistUseCase(WishlistMongoRepositoryAdapter wishlistMongoRepositoryAdapter) {
        return new ListProductsFromCustomerWishlistUseCase(wishlistMongoRepositoryAdapter);
    }

    @Bean
    public RemoveProductFromCustomerWishlistUseCase removeProductFromCustomerWishlistUseCase(WishlistMongoRepositoryAdapter wishlistMongoRepositoryAdapter) {
        return new RemoveProductFromCustomerWishlistUseCase(wishlistMongoRepositoryAdapter);
    }
}
