package wishlist.infrastructure.config;

import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public class TestcontainersInitializer {
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.4.2"));

    static {
        mongoDBContainer.start();
        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
    }
}