package wishlist.infrastructure.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "wishlists")
@CompoundIndex(name = "customerId_unique_idx", def = "{'customerId': 1}", unique = true)
public class WishlistDocument {
    @Id
    private String id;
    private String customerId;
    private List<ProductDocument> products;

    public WishlistDocument() {
    }

    public WishlistDocument(String customerId, List<ProductDocument> products) {
        this.customerId = customerId;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<ProductDocument> getProducts() {
        return products;
    }
}
