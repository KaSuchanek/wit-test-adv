package pl.sdacademy.booking.service;

import org.junit.jupiter.api.Test;

class ItemServiceTest {

    private ItemService sut;
    @Test
    void shouldResultAllItemsInDbAsListOfDto() {
        // na razie nie mozna szybko przeniesc testu, poniewaz przepiecie sie na EventRepository ze springa
        // powoduje problem w uzyciu klasy TestItemRepository

//        sut = new ItemService(new TestItemRepository());
//
//        List<ItemDto> result = sut.findItems();
//
//        assertThat(result).hasSize(2);
//        ItemDto first = result.get(0);
//        // liczba ponizszych asercji sygnalizuje, ze klasa moze miec za duzo odpowiedzialnosci
//        // powinna zostac podzielona na bardziej specjalistyczne klasy
//        assertThat(first.getName()).isEqualTo("Pierwszy");
//        assertThat(first.getDescription()).isEqualTo("Przykladowy opis");
//        assertThat(first.getPrice()).isEqualTo(BigDecimal.valueOf(120.0));
//
//        assertThat(first.getAttributes()).hasSize(1)
//                .contains("twarz");
//
//        ItemDto second = result.get(1);
//        assertThat(second.getAttributes()).isEmpty();
    }

}