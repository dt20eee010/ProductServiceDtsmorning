package dev.nishant.productservicedtsmorning.services;

import dev.nishant.productservicedtsmorning.dtos.UpdateProductDtos;
import dev.nishant.productservicedtsmorning.exception.ProductNotFoundExceptions;
import dev.nishant.productservicedtsmorning.models.Category;
import dev.nishant.productservicedtsmorning.models.Product;
import dev.nishant.productservicedtsmorning.repositories.CategoryRepository;
import dev.nishant.productservicedtsmorning.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service("SelfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long ProductId) throws ProductNotFoundExceptions {
        return productRepository.findByIdIs(ProductId);
    }


    @Override
    public Product createProduct(String title, double price, String description, String image, String category) {
        Product product= new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);
        Category category1 = categoryRepository.findByTitle(category);
        if(category1==null)
        {
            category1 = new Category();
            category1.setTitle(category);
//            category1= categoryRepository.save(category1);
        }
        product.setCategory(category1);
        Product saveproduct = productRepository.save(product);
        return saveproduct;
    }

    @Override
    public Product deleteProduct(Long ProductId) throws ProductNotFoundExceptions {
        Product product = productRepository.findByIdIs(ProductId);
        if (product == null) {
            throw new ProductNotFoundExceptions("Product not found with id: " + ProductId);
        }
        product.setDeleted(true);
        Product deletedProduct =productRepository.save(product);
        return deletedProduct;
    }

    @Override
    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }

    @Override
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProduct(String Category) {
        Category category1 = categoryRepository.findByTitle(Category);
        return productRepository.findAllByCategory(category1);
    }
    @Override
    public Product patchProduct(Long productId, String title, double price, String description, String image, String category){
        Product product = productRepository.findByIdIs(productId);
        if (product == null) {
            return null;
        }
        // Update only the fields that are not null in the parameters
        if (title != null) {
            product.setTitle(title);
        }
        if (description != null) {
            product.setDescription(description);
        }
        if (image != null) {
            product.setImageUrl(image);
        }
        if (category != null) {
            Category categoryProd = new Category();
            categoryProd.setTitle(category);
            product.setCategory(categoryProd);
        }
        product.setPrice(price);
        // Save the updated product back to the repository
        Product updatedProduct = productRepository.save(product);
        return updatedProduct;

    }
}
