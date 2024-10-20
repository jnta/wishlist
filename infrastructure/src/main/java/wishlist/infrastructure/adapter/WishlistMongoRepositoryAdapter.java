package wishlist.infrastructure.adapter;

import org.springframework.stereotype.Component;
import wishlist.domain.entity.Wishlist;
import wishlist.domain.repository.WishlistRepository;
import wishlist.infrastructure.mapper.WishlistMapper;
import wishlist.infrastructure.mongo.repository.WishlistMongoRepository;

import java.util.Optional;

@Component
public class WishlistMongoRepositoryAdapter implements WishlistRepository {
    private final WishlistMongoRepository repository;

    public WishlistMongoRepositoryAdapter(WishlistMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wishlist save(Wishlist wishlist) {
        var wishlistDocument = repository.save(WishlistMapper.toWishlistDocument(wishlist));
        return WishlistMapper.toWishlist(wishlistDocument);
    }

    @Override
    public Optional<Wishlist> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId)
                .map(WishlistMapper::toWishlist);
    }

    @Override
    public Optional<Wishlist> findByProductIdAndCustomerId(String productId, String customerId) {
        return repository.findByProductIdAndCustomerId(productId, customerId)
                .map(WishlistMapper::toWishlist);
    }

    @Override
    public void deleteProductFromCustomerWishlist(String productId, String customerId) {
        repository.deleteProductFromCustomerWishlist(productId, customerId);
    }
}
