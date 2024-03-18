package dev.nishant.productservicedtsmorning.controller;
import dev.nishant.productservicedtsmorning.dtos.CreateProductDtos;
import dev.nishant.productservicedtsmorning.models.Category;
import dev.nishant.productservicedtsmorning.models.Product;
import dev.nishant.productservicedtsmorning.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductDtos request){
            return productService.createProduct(request.getTitle(),request.getPrice(),request.getDescription(),request.getImage(),request.getCategory());
    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long ProductId){
        return  productService.getSingleProduct(ProductId);
    }
    @GetMapping("/products")
    public ArrayList<Product> getAllProduct(){

        return productService.getAllProduct();
    }

    @PostMapping("/products/update")
    public void updateProducts (){

    }
    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Long ProductId){
        return  productService.deleteProduct(ProductId);
    }

    @GetMapping("/products/categories")
    public List<Category> getCategory(){
        return productService.getCategory();
    }
    @GetMapping("/products/category/{category}")
    public List<Product> getProduct(@PathVariable("category") String category){
        return productService.getProduct(category);
    }
}
