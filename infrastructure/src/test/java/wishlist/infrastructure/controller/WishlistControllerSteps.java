package wishlist.infrastructure.controller;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import wishlist.application.usecase.AddProductToCustomerWishlistUseCase;
import wishlist.application.usecase.IsProductInCustomerWishlistUseCase;
import wishlist.application.usecase.ListProductsFromCustomerWishlistUseCase;
import wishlist.application.usecase.RemoveProductFromCustomerWishlistUseCase;
import wishlist.infrastructure.config.TestConfig;
import wishlist.infrastructure.dto.ProductDTO;
import wishlist.infrastructure.dto.WishlistDTO;
import wishlist.infrastructure.mapper.ProductMapper;
import wishlist.infrastructure.mapper.WishlistMapper;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ContextConfiguration(classes = TestConfig.class)
@CucumberContextConfiguration
@SpringBootTest
public class WishlistControllerSteps {

    @Autowired
    private WishlistController wishlistController;
    @Autowired
    private AddProductToCustomerWishlistUseCase addProductToWishlistUseCase;
    @Autowired
    private IsProductInCustomerWishlistUseCase isProductOnWishlistUseCase;
    @Autowired
    private RemoveProductFromCustomerWishlistUseCase removeProductFromWishlistUseCase;
    @Autowired
    private ListProductsFromCustomerWishlistUseCase listProductsFromWishlistUseCase;
    private ResponseEntity<?> response;

    // AddProductToWishlist.feature
    @Given("a customer with id {string}")
    public void aCustomerWithId(String customerId) {
    }

    @When("the customer adds a product with id {string} and name {string} to the wishlist")
    public void theCustomerAddsAProductWithIdAndNameToTheWishlist(String productId, String productName) {
        ProductDTO productDTO = new ProductDTO(productId, productName);
        Mockito.when(addProductToWishlistUseCase.execute(any(), anyString())).thenReturn(WishlistMapper.toDomain(new WishlistDTO("1", "customer1", Set.of(productDTO))));
        response = wishlistController.addProductToWishlist("customer1", productDTO);
    }

    @Then("the response status should be {int} CREATED")
    public void the_response_status_should_be_created(Integer statusCode) {
        assertThat(response.getStatusCode().value()).isEqualTo(statusCode);
    }

    // RemoveProductFromWishlist.feature
    @Given("a customer with id {string} and a product with id {string} in the wishlist")
    public void aCustomerWithIdAndAProductWithIdInTheWishlist(String customerId, String productId) {
        WishlistDTO wishlistDTO = new WishlistDTO("1", customerId, Set.of(new ProductDTO(productId, "Product 1")));
        Mockito.when(addProductToWishlistUseCase.execute(any(), anyString())).thenReturn(WishlistMapper.toDomain(wishlistDTO));
        wishlistController.addProductToWishlist(customerId, new ProductDTO(productId, "Product 1"));
    }

    @When("the customer removes the product with id {string} from the wishlist")
    public void theCustomerRemovesTheProductWithIdFromTheWishlist(String productId) {
        response = wishlistController.removeProductFromWishlist("customer1", productId);
    }

    @Then("the response status should be {int} NO CONTENT")
    public void the_response_status_should_be_no_content(Integer statusCode) {
        assertThat(response.getStatusCode().value()).isEqualTo(statusCode);
    }

    // IsProductOnWishlist.feature
    @When("the customer checks if the product with id {string} is in the wishlist")
    public void theCustomerChecksIfTheProductWithIdIsInTheWishlist(String productId) {
        Mockito.when(isProductOnWishlistUseCase.execute(anyString(), anyString())).thenReturn(true);
        response = wishlistController.isProductOnWishlist("customer1", productId);
    }

    @Then("the response status should be {int} OK")
    public void the_response_status_should_be_ok(Integer statusCode) {
        assertThat(response.getStatusCode().value()).isEqualTo(statusCode);
    }

    // ListProductsFromWishlist.feature
    @When("the customer lists all products in the wishlist")
    public void theCustomerListsAllProductsInTheWishlist() {
        ProductDTO productDTO = new ProductDTO("product1", "Product 1");
        Mockito.when(listProductsFromWishlistUseCase.execute(anyString())).thenReturn(Set.of(ProductMapper.toProduct(productDTO)));
        response = wishlistController.listProductsFromWishlist("customer1");
    }

    @Then("the response should contain the product with id {string} and name {string}")
    public void theResponseShouldContainTheProductWithIdAndName(String productId, String productName) {
        assertThat(response.getBody()).isInstanceOf(List.class);
        List<ProductDTO> products = (List<ProductDTO>) response.getBody();
        assertThat(products).contains(new ProductDTO(productId, productName));
    }
}