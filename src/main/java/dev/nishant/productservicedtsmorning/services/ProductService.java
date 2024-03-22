package dev.nishant.productservicedtsmorning.services;
import dev.nishant.productservicedtsmorning.dtos.UpdateProductDtos;
import dev.nishant.productservicedtsmorning.exception.ProductNotFoundExceptions;
import dev.nishant.productservicedtsmorning.models.Category;
import dev.nishant.productservicedtsmorning.models.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public interface ProductService {
    Product getSingleProduct(Long ProductId) throws ProductNotFoundExceptions;
    Product createProduct(String title, double price, String description,String image,String category);
    Product deleteProduct(Long ProductId) throws ProductNotFoundExceptions;
    List<Product> getAllProduct();
    List<Category> getCategory();
    List<Product> getProduct(String Category);
    Product patchProduct(Long productId,String title, double price, String description, String image, String category);
}
