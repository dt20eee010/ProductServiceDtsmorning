package dev.nishant.productservicedtsmorning.services;
import dev.nishant.productservicedtsmorning.models.Category;
import dev.nishant.productservicedtsmorning.models.Product;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Service
public interface ProductService {
    Product getSingleProduct(Long ProductId);
    Product createProduct(String title, double price, String description,String image,String category);
    Product deleteProduct(Long ProductId);
    ArrayList<Product> getAllProduct();
    List<Category> getCategory();
    List<Product> getProduct(String category);
}
