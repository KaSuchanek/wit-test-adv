package pl.sdacademy.booking.repository;

import org.springframework.data.repository.ListCrudRepository;
import pl.sdacademy.booking.data.EventEntity;

import java.time.LocalDateTime;

public interface EventRepository extends ListCrudRepository<EventEntity, Long> {

    Long findEventsByFrom(LocalDateTime date);
}