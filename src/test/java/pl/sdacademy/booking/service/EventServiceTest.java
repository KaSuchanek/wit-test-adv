package pl.sdacademy.booking.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sdacademy.booking.data.EventEntity;
import pl.sdacademy.booking.data.ItemEntity;
import pl.sdacademy.booking.model.EventDto;
import pl.sdacademy.booking.repository.EventRepository;
import pl.sdacademy.booking.repository.ItemRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;
    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private EventService sut;

    @Test
    void shouldResultAllEventsInDbAsListOfDto() {
        // tak jak recznie ustawiane byly dane (poprzednio) w TestEventRepository,
        // tak teraz musimy okreslic
        // co maja zwracac mockowane repozytoria
        when(eventRepository.findAll()).thenReturn(
                provideListOf2EventsRelatedTo1Item()
        );
        // jezeli nie dodamy logiki pobrania wszystkich item nasza logika nie bedzie dzialac
        // to kolejny sygnal, ze cos jest nie tak z nasza logika. Musimy odpowiedziec na pytanie:
        // czy moze w bazie istniec event, wskazujacy na item, a tego item nie byc w bazie?
        // jezeli tak - musimy poprawic logike
        // jezeli nie - musimy poprawic test
        // moje zalozenie - nie jest to mozliwe, zatem poprawiam test dopisujac jak mock ma udawac co jest w bazie
        when(itemRepository.findAll()).thenReturn(
                provideItemUsedByEvents()
        );


        List<EventDto> result = sut.findEvents();

        assertThat(result).hasSize(2);
        EventDto first = result.get(0);
        // liczba ponizszych asercji sygnalizuje, ze klasa moze miec za duzo odpowiedzialnosci
        // powinna zostac podzielona na bardziej specjalistyczne klasy
        assertThat(first.getItemName()).isEqualTo("jeden");
        assertThat(first.getItemPrice()).isEqualTo(BigDecimal.valueOf(120.0));
        assertThat(first.getFromTime()).isEqualTo(LocalDateTime.of(2123, 9, 23, 8, 10, 00));
        assertThat(first.getToTime()).isEqualTo(LocalDateTime.of(2123, 9, 23, 8, 20, 00));


        EventDto second = result.get(1);
        assertThat(second.getItemName()).isEqualTo("jeden");
    }

    @Disabled
    @Test
    void shouldReturnEmptyListIfThereAreEventsWithoutRelatedItems() {
        when(eventRepository.findAll()).thenReturn(
                provideListOf2EventsRelatedTo1Item()
        );

        List<EventDto> result = sut.findEvents();

        assertThat(result).isEmpty();
    }

    private static List<ItemEntity> provideItemUsedByEvents() {
        return List.of(ItemEntity.builder()
                // musi byc to samo co w mockowania zachowania eventRepository.findAll()  -patrz wyzej
                .id(2L)
                .name("jeden")
                .price(BigDecimal.valueOf(120.))
                .build());
    }

    private static List<EventEntity> provideListOf2EventsRelatedTo1Item() {
        return List.of(
                EventEntity.builder()
                        .id(1L)
                        .itemId(2L)
                        // wraca problem kontroli czasu - problem z przygotowaniem danych testowych sygnalizuje, ze nadal
                        // mamy nienajlepsza logike
                        .from(LocalDateTime.of(2123, 9, 23, 8, 10))
                        .to(LocalDateTime.of(2123, 9, 23, 8, 20))
                        .build(),
                EventEntity.builder()
                        .id(1L)
                        .itemId(2L)
                        .from(LocalDateTime.of(2123, 9, 23, 8, 30))
                        .to(LocalDateTime.of(2123, 9, 23, 8, 40))
                        .build()
        );
    }


}