package wishlist.infrastructure.mapper;

import wishlist.domain.entity.Product;
import wishlist.infrastructure.dto.ProductDTO;
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

    public static Product toProduct(ProductDTO productDTO) {
        return new Product(productDTO.id(), productDTO.name());
    }

    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(product.id(), product.name());
    }
}
