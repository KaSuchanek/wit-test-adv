package pl.sdacademy.booking.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "booking")
@Data
public class BookingEntity {
    @Id
    private Long id;
    private long eventId;
    // in final solution it would be rather another entity which represents customer
    private String customerName;
    private String customerContact;
}
