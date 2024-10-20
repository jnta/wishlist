package wishlist.application.usecase;

import wishlist.domain.repository.WishlistRepository;

public class IsProductInCustomerWishlistUseCase {
    private final WishlistRepository wishlistRepository;

    public IsProductInCustomerWishlistUseCase(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public boolean execute(String productId, String customerId) {
        return wishlistRepository.findByProductIdAndCustomerId(productId, customerId).isPresent();
    }
}
