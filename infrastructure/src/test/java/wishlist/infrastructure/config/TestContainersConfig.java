package wishlist.infrastructure.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration
public class TestContainersConfig {

    @Bean
    public MongoDBContainer mongoDBContainer() {
        MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
        mongoDBContainer.start();
        return mongoDBContainer;
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDBContainer mongoDBContainer) {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoDBContainer.getReplicaSetUrl()));
    }
}