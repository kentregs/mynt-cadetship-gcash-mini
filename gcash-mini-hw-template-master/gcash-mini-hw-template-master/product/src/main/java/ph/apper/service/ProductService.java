package ph.apper.service;
import org.springframework.stereotype.Component;
import ph.apper.exception.*;
import ph.apper.payload.AddProductRequest;
import ph.apper.payload.AddProductResponse;
import ph.apper.payload.PurchaseProductRequest;
import ph.apper.payload.ProductData;

@Component
public interface ProductService {
    AddProductResponse addProduct(AddProductRequest request);
    void purchase(PurchaseProductRequest request) throws InvalidProductPurchaseException, ProductNotFoundException;
    public ProductData getProduct(String productId) throws ProductNotFoundException;
}