package wishlist.infrastructure.dto;

import java.util.Collection;

public record WishlistDTO(String id, String customerId, Collection<ProductDTO> products) {
}
