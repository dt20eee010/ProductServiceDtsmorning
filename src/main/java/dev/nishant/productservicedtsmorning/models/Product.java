package dev.nishant.productservicedtsmorning.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private Category category;

}
