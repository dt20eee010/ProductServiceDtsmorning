package dev.nishant.productservicedtsmorning.dtos;

import dev.nishant.productservicedtsmorning.models.Category;
import dev.nishant.productservicedtsmorning.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductDtos {

    private Long id;
    private String title;
    private double price;
    private String Category;
    private String description;
    private String image;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        Category productCategory = new Category();
        productCategory.setTitle(Category);
        product.setCategory(productCategory);
        return product;
    }

}
