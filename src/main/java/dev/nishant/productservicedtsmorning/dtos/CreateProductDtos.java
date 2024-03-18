package dev.nishant.productservicedtsmorning.dtos;
import dev.nishant.productservicedtsmorning.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDtos {

    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

}
