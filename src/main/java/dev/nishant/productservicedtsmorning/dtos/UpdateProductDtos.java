package dev.nishant.productservicedtsmorning.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDtos {

    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

}
