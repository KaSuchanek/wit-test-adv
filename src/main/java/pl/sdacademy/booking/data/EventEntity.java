package pl.sdacademy.booking.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "event")
@Data
public class EventEntity {
    @Id
    private Long id;
    private long itemId;
    @Column("time_from")
    private LocalDateTime from;
    @Column("time_to")
    private LocalDateTime to;
}
