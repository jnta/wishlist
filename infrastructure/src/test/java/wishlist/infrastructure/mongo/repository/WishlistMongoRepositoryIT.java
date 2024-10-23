package wishlist.infrastructure.mongo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import wishlist.domain.entity.Product;
import wishlist.infrastructure.config.TestContainersConfig;
import wishlist.infrastructure.mapper.ProductMapper;
import wishlist.infrastructure.mongo.documents.WishlistDocument;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {TestContainersConfig.class})
@WebAppConfiguration
class WishlistMongoRepositoryIT {

    @Autowired
    private WishlistMongoRepository wishlistMongoRepository;

    @Test
    void saveAndFindByCustomerId() {
        WishlistDocument wishlistDocument = new WishlistDocument("1", "customer1", Set.of());
        wishlistMongoRepository.save(wishlistDocument);

        Optional<WishlistDocument> foundDocument = wishlistMongoRepository.findByCustomerId("customer1");
        assertThat(foundDocument).isPresent();
        assertThat(foundDocument.get().getCustomerId()).isEqualTo("customer1");
    }

    @Test
    void findByCustomerIdReturnsEmptyWhenNotFound() {
        Optional<WishlistDocument> foundDocument = wishlistMongoRepository.findByCustomerId("nonexistent");
        assertThat(foundDocument).isNotPresent();
    }

    @Test
    void findByProductIdAndCustomerId() {
        WishlistDocument wishlistDocument = new WishlistDocument("1", "customer1", Set.of(ProductMapper.toProductDocument(new Product("product1", "Product 1"))));
        wishlistMongoRepository.save(wishlistDocument);

        Optional<WishlistDocument> foundDocument = wishlistMongoRepository.findByProductIdAndCustomerId("product1", "customer1");
        assertThat(foundDocument).isPresent();
        assertThat(foundDocument.get().getCustomerId()).isEqualTo("customer1");
    }

    @Test
    void findByProductIdAndCustomerIdReturnsEmptyWhenNotFound() {
        Optional<WishlistDocument> foundDocument = wishlistMongoRepository.findByProductIdAndCustomerId("nonexistent", "customer1");
        assertThat(foundDocument).isNotPresent();
    }

    @Test
    void deleteProductFromCustomerWishlist() {
        WishlistDocument wishlistDocument = new WishlistDocument("1", "customer1", Set.of(ProductMapper.toProductDocument(new Product("product1", "Product 1"))));
        wishlistMongoRepository.save(wishlistDocument);

        wishlistMongoRepository.deleteProductFromCustomerWishlist("product1", "customer1");

        Optional<WishlistDocument> foundDocument = wishlistMongoRepository.findByProductIdAndCustomerId("product1", "customer1");
        assertThat(foundDocument).isNotPresent();
    }
}