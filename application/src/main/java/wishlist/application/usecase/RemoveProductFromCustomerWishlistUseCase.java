package wishlist.application.usecase;

import wishlist.domain.entity.Wishlist;
import wishlist.domain.exception.WishlistNotFoundException;
import wishlist.domain.repository.WishlistRepository;

import java.util.Optional;

public class RemoveProductFromCustomerWishlistUseCase {
    private final WishlistRepository wishlistRepository;

    public RemoveProductFromCustomerWishlistUseCase(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void execute(String productId, String customerId) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findByCustomerId(customerId);
        if (wishlistOptional.isEmpty()) {
            throw new WishlistNotFoundException("Wishlist for customer not found");
        }
        wishlistRepository.deleteProductFromCustomerWishlist(productId, customerId);
    }
}
