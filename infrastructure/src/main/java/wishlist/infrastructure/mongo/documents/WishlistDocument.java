package wishlist.infrastructure.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document("wishlist")
public class WishlistDocument {
    private final Set<ProductDocument> products = new HashSet<>();
    @Id
    private String id;
    @Indexed(unique = true)
    private String customerId;

    public WishlistDocument() {
    }

    public WishlistDocument(String id, String customerId, Collection<ProductDocument> products) {
        this.id = id;
        this.customerId = customerId;
        this.products.addAll(products);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Set<ProductDocument> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductDocument> products) {
        this.products.clear();
        this.products.addAll(products);
    }

    @Override
    public String toString() {
        return "WishlistDocument{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WishlistDocument that = (WishlistDocument) o;
        return Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(customerId);
    }
}
