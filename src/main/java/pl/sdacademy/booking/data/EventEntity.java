package pl.sdacademy.booking.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
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
