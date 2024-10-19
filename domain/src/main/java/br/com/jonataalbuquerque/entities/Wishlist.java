package br.com.jonataalbuquerque.entities;

import br.com.jonataalbuquerque.exception.ProductLimitExceededException;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {
    private String id;
    private String customerId;
    private List<Product> products = new ArrayList<>();


    public void addProduct(Product product) {
        if (products.size() >= 20) {
            throw new ProductLimitExceededException("Wishlist can't contain more than 20 products");
        }
        this.products.add(product);
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
