package pl.sdacademy.booking.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewItemDto {
    private String name;
    private String description;
    private BigDecimal price;
}