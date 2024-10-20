package wishlist.infrastructure.mapper;

import wishlist.domain.entity.Wishlist;
import wishlist.infrastructure.mongo.documents.WishlistDocument;

public class WishlistMapper {
    private WishlistMapper() {
    }

    public static WishlistDocument toWishlistDocument(Wishlist wishlist) {
        return new WishlistDocument(wishlist.getCustomerId(), wishlist.getProducts().stream()
                .map(ProductMapper::toProductDocument)
                .toList());
    }

    public static Wishlist toWishlist(WishlistDocument wishlistDocument) {
        return new Wishlist(wishlistDocument.getId(), wishlistDocument.getCustomerId(), wishlistDocument.getProducts().stream()
                .map(ProductMapper::toProduct)
                .toList());
    }
}
