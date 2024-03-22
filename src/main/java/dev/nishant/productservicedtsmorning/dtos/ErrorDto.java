package dev.nishant.productservicedtsmorning.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    @JsonProperty("message")
    private String message;
}
