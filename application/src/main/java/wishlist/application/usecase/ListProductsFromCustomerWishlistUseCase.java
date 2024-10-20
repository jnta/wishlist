package wishlist.application.usecase;

import wishlist.domain.entity.Product;
import wishlist.domain.exception.WishlistNotFoundException;
import wishlist.domain.repository.WishlistRepository;

import java.util.Set;

public class ListProductsFromCustomerWishlistUseCase {
    private final WishlistRepository wishlistRepository;

    public ListProductsFromCustomerWishlistUseCase(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public Set<Product> execute(String customerId) {
        var wishlist = wishlistRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new WishlistNotFoundException("Wishlist not found by customerId: " + customerId));
        return wishlist.getProducts();
    }
}
