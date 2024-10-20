package wishlist.infrastructure.mapper;

import wishlist.domain.entity.Product;
import wishlist.infrastructure.dto.ProductRequest;
import wishlist.infrastructure.mongo.documents.ProductDocument;

public class ProductMapper {
    private ProductMapper() {
    }

    public static ProductDocument toProductDocument(Product product) {
        return new ProductDocument(product.id(), product.name());
    }

    public static Product toProduct(ProductDocument productDocument) {
        return new Product(productDocument.getId(), productDocument.getName());
    }

    public static Product toProduct(ProductRequest productRequest) {
        return new Product(productRequest.id(), productRequest.name());
    }
}
