package wishlist.infrastructure.mongo.documents;

public class ProductDocument {
    private String id;
    private String name;

    public ProductDocument() {
    }

    public ProductDocument(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
