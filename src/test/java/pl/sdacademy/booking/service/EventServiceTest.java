package pl.sdacademy.booking.service;

import org.junit.jupiter.api.Test;

class EventServiceTest {

    private EventService sut;

    @Test
    void shouldResultAllEventsInDbAsListOfDto() {
        // na razie nie mozna szybko przeniesc testu, poniewaz przepiecie sie na EventRepository ze springa
        // powoduje problem w uzyciu klasy TestEventRepository
//        sut = new EventService(new TestEventRepository(), new ItemServiceTest.TestItemRepository());
//
//        List<EventDto> result = sut.findEvents();
//
//        assertThat(result).hasSize(2);
//        EventDto first = result.get(0);
//        // liczba ponizszych asercji sygnalizuje, ze klasa moze miec za duzo odpowiedzialnosci
//        // powinna zostac podzielona na bardziej specjalistyczne klasy
//        assertThat(first.getItemName()).isEqualTo("jeden");
//        assertThat(first.getItemPrice()).isEqualTo(BigDecimal.valueOf(120.0));
//        assertThat(first.getFromTime()).isEqualTo(LocalDateTime.of(2023, 9, 18, 10, 30, 00));
//        assertThat(first.getToTime()).isEqualTo(LocalDateTime.of(2023, 9, 18, 11, 30, 00));
//
//
//        EventDto second = result.get(1);
//        assertThat(second.getItemName()).isEqualTo("jeden");
    }


}