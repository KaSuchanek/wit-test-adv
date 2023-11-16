package pl.sdacademy.booking.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sdacademy.booking.model.EventDto;
import pl.sdacademy.booking.service.EventService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventService eventService;

    @Test
    void shouldReturnListOfEvents() throws Exception {
        when(eventService.findEvents()).thenReturn(
                List.of(EventDto.builder()
                                .id(1L)
                                .itemName("test1")
                                .fromTime(LocalDateTime.of(2023, 9, 23, 10, 0))
                                .toTime(LocalDateTime.of(2023, 9, 23, 10, 20))
                                .itemPrice(BigDecimal.valueOf(80))
                                .build(),
                        EventDto.builder()
                                .id(2L)
                                .itemName("test2")
                                .fromTime(LocalDateTime.of(2023, 9, 23, 10, 0))
                                .toTime(LocalDateTime.of(2023, 9, 23, 10, 20))
                                .itemPrice(BigDecimal.valueOf(120))
                                .build())
        );

        mvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}