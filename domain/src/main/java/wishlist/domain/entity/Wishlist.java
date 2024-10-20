package wishlist.domain.entity;

import wishlist.domain.exception.ProductLimitExceededException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Wishlist {
    private final Set<Product> products = new HashSet<>();
    private String id;
    private String customerId;

    public Wishlist(String customerId) {
        this.customerId = customerId;
    }

    public Wishlist(String id, String customerId, List<Product> products) {
        this.id = id;
        this.customerId = customerId;
        this.products.addAll(products);
    }

    public void addProduct(Product product) {
        if (products.size() >= 20) {
            throw new ProductLimitExceededException("Wishlist can't contain more than 20 products");
        }
        this.products.add(product);
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Set<Product> getProducts() {
        return products;
    }
}
