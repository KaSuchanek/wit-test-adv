package pl.sdacademy.booking.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
@Data
public class ItemEntity {

    @Id
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
