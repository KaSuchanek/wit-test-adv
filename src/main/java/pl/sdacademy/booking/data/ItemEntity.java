package pl.sdacademy.booking.data;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table(name = "item")
@Data
public class ItemEntity {

    @Id
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
