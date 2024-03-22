package dev.nishant.productservicedtsmorning.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundExceptions extends Exception {
    public ProductNotFoundExceptions(String message)
    {
        super(message);
    }
}
