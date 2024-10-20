package wishlist.infrastructure.mongo.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import wishlist.infrastructure.mongo.documents.WishlistDocument;

import java.util.Optional;

@Repository
public class WishlistMongoRepository {
    private static final String CUSTOMER_ID = "customerId";
    private final MongoTemplate mongoTemplate;

    public WishlistMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public WishlistDocument save(WishlistDocument wishlistDocument) {
        return mongoTemplate.save(wishlistDocument);
    }

    public Optional<WishlistDocument> findByCustomerId(String customerId) {
        return Optional.ofNullable(mongoTemplate.findOne(
                Query.query(Criteria.where(CUSTOMER_ID).is(customerId)),
                WishlistDocument.class
        ));
    }

    public Optional<WishlistDocument> findByProductIdAndCustomerId(String productId, String customerId) {
        return Optional.ofNullable(mongoTemplate.findOne(
                Query.query(Criteria.where(CUSTOMER_ID).is(customerId).and("products.productId").is(productId)),
                WishlistDocument.class
        ));
    }

    public void deleteProductFromCustomerWishlist(String productId, String customerId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where(CUSTOMER_ID).is(customerId)),
                new Update().pull("products", Query.query(Criteria.where("productId").is(productId))),
                WishlistDocument.class
        );
    }
}
