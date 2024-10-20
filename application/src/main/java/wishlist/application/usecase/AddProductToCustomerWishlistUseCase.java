package wishlist.application.usecase;

import wishlist.domain.entity.Product;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.repository.WishlistRepository;

public class AddProductToCustomerWishlistUseCase {
    private final WishlistRepository wishlistRepository;

    public AddProductToCustomerWishlistUseCase(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public Wishlist execute(Product product, String customerId) {
        var wishlist = wishlistRepository.findByCustomerId(customerId)
                .orElse(new Wishlist(customerId));
        wishlist.addProduct(product);
        return wishlistRepository.save(wishlist);
    }
}
