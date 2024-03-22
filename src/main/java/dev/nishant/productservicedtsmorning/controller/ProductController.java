package dev.nishant.productservicedtsmorning.controller;
import dev.nishant.productservicedtsmorning.dtos.CreateProductDtos;
import dev.nishant.productservicedtsmorning.dtos.ErrorDto;
import dev.nishant.productservicedtsmorning.dtos.UpdateProductDtos;
import dev.nishant.productservicedtsmorning.exception.ProductNotFoundExceptions;
import dev.nishant.productservicedtsmorning.models.Category;
import dev.nishant.productservicedtsmorning.models.Product;
import dev.nishant.productservicedtsmorning.services.ProductService;
import dev.nishant.productservicedtsmorning.services.SelfProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(@Qualifier("SelfProductService") ProductService productService){

        this.productService=productService;
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductDtos request){

        return productService.createProduct(request.getTitle(),request.getPrice(),request.getDescription(),request.getImage(),request.getCategory());
    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long ProductId) throws ProductNotFoundExceptions {
        return  productService.getSingleProduct(ProductId);
    }
    @GetMapping("/products")
    public List<Product> getAllProduct(){

        return productService.getAllProduct();
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Long ProductId) throws ProductNotFoundExceptions{
        return  productService.deleteProduct(ProductId);
    }

    @GetMapping("/products/categories")
    public List<Category> getCategory(){
        return productService.getCategory();
    }
    @GetMapping("/products/category/{category}")
    public List<Product> getProduct(@PathVariable("category") String Category){
        return productService.getProduct(Category);
    }

    @PatchMapping("/products/{productId}")
    public Product patchProduct(@PathVariable("productId") Long productId,@RequestBody UpdateProductDtos request) {

        return productService.patchProduct(productId,request.getTitle(),request.getPrice(),request.getDescription(),request.getImage(),request.getCategory());
    }

    @ExceptionHandler(ProductNotFoundExceptions.class)
    public ResponseEntity<ErrorDto> handleNotFoundException(ProductNotFoundExceptions exception){
        ErrorDto errordto = new ErrorDto();
        errordto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errordto, HttpStatus.FOUND);
    }
}
