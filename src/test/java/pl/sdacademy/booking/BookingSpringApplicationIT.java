package pl.sdacademy.booking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sdacademy.booking.service.EventService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookingSpringApplicationIT {

    @Autowired
    private EventService eventService;

    @Test
    void contextLoads() {
        assertThat(eventService).isNotNull();
    }

}
