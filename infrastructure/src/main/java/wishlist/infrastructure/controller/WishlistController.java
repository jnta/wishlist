package wishlist.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wishlist.application.usecase.AddProductToCustomerWishlistUseCase;
import wishlist.application.usecase.IsProductInCustomerWishlistUseCase;
import wishlist.application.usecase.ListProductsFromCustomerWishlistUseCase;
import wishlist.application.usecase.RemoveProductFromCustomerWishlistUseCase;
import wishlist.infrastructure.dto.ProductDTO;
import wishlist.infrastructure.dto.WishlistDTO;
import wishlist.infrastructure.mapper.ProductMapper;
import wishlist.infrastructure.mapper.WishlistMapper;

import java.util.List;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {
    private final AddProductToCustomerWishlistUseCase addProductToWishlistUseCase;
    private final IsProductInCustomerWishlistUseCase isProductOnWishlistUseCase;
    private final RemoveProductFromCustomerWishlistUseCase removeProductFromWishlistUseCase;
    private final ListProductsFromCustomerWishlistUseCase listProductsFromWishlistUseCase;

    public WishlistController(AddProductToCustomerWishlistUseCase addProductToWishlistUseCase,
                              IsProductInCustomerWishlistUseCase isProductOnWishlistUseCase,
                              RemoveProductFromCustomerWishlistUseCase removeProductFromWishlistUseCase,
                              ListProductsFromCustomerWishlistUseCase listProductsFromWishlistUseCase) {
        this.addProductToWishlistUseCase = addProductToWishlistUseCase;
        this.isProductOnWishlistUseCase = isProductOnWishlistUseCase;
        this.removeProductFromWishlistUseCase = removeProductFromWishlistUseCase;
        this.listProductsFromWishlistUseCase = listProductsFromWishlistUseCase;
    }

    @PostMapping("/{customerId}/products")
    public ResponseEntity<WishlistDTO> addProductToWishlist(@PathVariable String customerId, @RequestBody ProductDTO productDTO) {
        var wishlist = addProductToWishlistUseCase.execute(ProductMapper.toProduct(productDTO), customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(WishlistMapper.toDTO(wishlist));
    }

    @DeleteMapping("/{customerId}/products/{productId}")
    public ResponseEntity<Void> removeProductFromWishlist(@PathVariable String customerId, @PathVariable String productId) {
        removeProductFromWishlistUseCase.execute(productId, customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{customerId}/products/{productId}/exists")
    public ResponseEntity<Void> isProductOnWishlist(@PathVariable String customerId, @PathVariable String productId) {
        return isProductOnWishlistUseCase.execute(productId, customerId)
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{customerId}/products")
    public ResponseEntity<List<ProductDTO>> listProductsFromWishlist(@PathVariable String customerId) {
        var products = listProductsFromWishlistUseCase.execute(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(products.stream().map(ProductMapper::toDTO).toList());
    }
}
