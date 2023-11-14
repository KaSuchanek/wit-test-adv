package pl.sdacademy.booking.data;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "item_attribute")
@Data
public class ItemAttributeEntity {
    @Id
    private Long id;
    private String attributeName;
}
