package wishlist.domain.repository;

import wishlist.domain.entity.Wishlist;

import java.util.Optional;

public interface WishlistRepository {
    Wishlist save(Wishlist wishlist);

    Optional<Wishlist> findByCustomerId(String customerId);

    Optional<Wishlist> findByProductIdAndCustomerId(String productId, String customerId);

    void deleteProductFromCustomerWishlist(String productId, String customerId);
}
