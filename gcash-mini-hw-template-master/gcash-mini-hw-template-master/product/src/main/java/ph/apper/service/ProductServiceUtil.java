package ph.apper.service;

import org.springframework.stereotype.Service;
import ph.apper.domain.Product;
import ph.apper.payload.ProductData;

@Service
public class ProductServiceUtil {
    // convert User object to a UserData object
    public static ProductData toProductData(Product p) {
        ProductData productData = new ProductData();

        productData.setProductId(p.getProductId());
        productData.setPrice(p.getPrice());
        productData.setName(p.getName());

        return productData;
    }
}