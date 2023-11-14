package pl.sdacademy.booking.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class EventDto {

    private Long id;
    private String itemName;
    private BigDecimal itemPrice;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

}