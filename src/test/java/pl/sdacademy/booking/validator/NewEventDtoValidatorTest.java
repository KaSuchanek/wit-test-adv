package pl.sdacademy.booking.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.sdacademy.booking.model.NewEventDto;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Validator should")
class NewEventDtoValidatorTest {

    // domyslne ustawienie na poziomie testu uprasza inicjowanie tych ktore dzialaja bez kontroli czasu
    private Clock clock = Clock.systemUTC();

    @Test
    void shouldCheckIfEvenIsSet() {

        List<String> result = NewEventDtoValidator.validate(null, clock);

        assertThat(result).hasSize(1)
                .contains("Event must be defined - got null");

    }

    // nie stosuje komentarzy given,when,then - zamiast tego poszczegolne czesci rozdzielam pusta linia
    @Test
    void shouldCheckThatFromIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .fromTime(null)
                .toTime(LocalDateTime.of(2023, 9, 19, 19, 56))
                .build();

        List<String> result = NewEventDtoValidator.validate(input, clock);

        assertThat(result).hasSize(1)
                .contains("From is null");
    }

    @Test
    void shouldCheckThatToIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .toTime(null)
                .fromTime(LocalDateTime.of(2023, 9, 19, 19, 56))
                .build();

        List<String> result = NewEventDtoValidator.validate(input, clock);

        assertThat(result).hasSize(1)
                .contains("To is null");
    }

    @Test
    void shouldCheckThatToAndFromIsNull() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .toTime(null)
                .fromTime(null)
                .build();

        List<String> result = NewEventDtoValidator.validate(input, clock);

        assertThat(result).hasSize(2)
                .contains("To is null");
    }

    @Test
    void shouldCheckThatToIsToBeforeFrom() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .fromTime(LocalDateTime.of(2123, 9, 19, 9, 56))
                .toTime(LocalDateTime.of(2123, 9, 19, 9, 52))
                .build();

        List<String> result = NewEventDtoValidator.validate(input, clock);

        assertThat(result).hasSize(1)
                .contains("To is before from");
    }

    @Test
    void shouldCheckThatEventCannotBeOutOfWorkingHours_CaseAfter() {
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                // czas dlugo w przyszlosc powinien nas zabezpieczyc przed bledem - mozna tak to osiagnac ale nie ma 100% pewnosci
                .fromTime(LocalDateTime.of(2124, 9, 22, 18, 25))
                .toTime(LocalDateTime.of(2124, 9, 22, 18, 56))
                .build();

        List<String> result = NewEventDtoValidator.validate(input, clock);

        assertThat(result).hasSize(2)
                .contains("Event cannot be set out of the working hours - from 08:00 to 16:00");
    }

    @Test
    void shouldCheckThatEventCannotBeOutOfWorkingHours_CaseBefore() {
        // tutaj sami kontrolujemy czas wiec teraz na pewno wiemy kiedy jest przeszlosci - to jest pewniejsze
        clock = Clock.fixed(Instant.parse("2023-09-20T16:00:00Z"), ZoneId.systemDefault());
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .fromTime(LocalDateTime.of(2023, 9, 22, 7, 59))
                .toTime(LocalDateTime.of(2023, 9, 22, 8, 10))
                .build();

        List<String> result = NewEventDtoValidator.validate(input, clock);

        assertThat(result).hasSize(1)
                .contains("Event cannot be set out of the working hours - from 08:00 to 16:00");
    }

    @Test
    void shouldCheckThatEventCannotBeOutOfWorkingHours_CaseEndsAfter() {
        clock = Clock.fixed(Instant.parse("2023-09-20T16:00:00Z"), ZoneId.systemDefault());
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .fromTime(LocalDateTime.of(2023, 9, 22, 15, 45))
                .toTime(LocalDateTime.of(2023, 9, 22, 16, 1))
                .build();

        List<String> result = NewEventDtoValidator.validate(input, clock);

        assertThat(result).hasSize(1)
                .contains("Event cannot be set out of the working hours - from 08:00 to 16:00");
    }

    @Test
    void shouldCheckThatEventIsFromPast() {
        clock = Clock.fixed(Instant.parse("2124-09-29T16:00:00Z"), ZoneId.systemDefault());
        NewEventDto input = NewEventDto.builder()
                .itemName("przyklad")
                .fromTime(LocalDateTime.of(2124, 9, 22, 8, 25))
                .toTime(LocalDateTime.of(2124, 9, 22, 8, 30))
                .build();

        List<String> result = NewEventDtoValidator.validate(input, clock);

        assertThat(result).hasSize(1)
                .contains("Event cannot be set in the past");
    }

    @Test
    void shouldCheckThatItemNameCannotBeNull() {
        clock = Clock.fixed(Instant.parse("2022-09-29T16:00:00Z"), ZoneId.systemDefault());
        NewEventDto input = NewEventDto.builder()
                .itemName(null)
                // relatywny czas (LocalDateTime.now() jako dane testowe tez moze byc stosowany
                // ale musimy miec pewnosc ze spelnia warunki
                // w naszym przypadku z uwagi na godziny pracy nie ma takiej gwaranci
                .fromTime(LocalDateTime.of(2023, 9, 22, 8, 25))
                .toTime(LocalDateTime.of(2023, 9, 22, 8, 45))
                .build();

        List<String> result = NewEventDtoValidator.validate(input, clock);

        assertThat(result).hasSize(1)
                .contains("Item name must be set");

    }

    @Test
    void shouldCheckThatItemNameCannotBeEmpty() {
        clock = Clock.fixed(Instant.parse("2022-09-29T16:00:00Z"), ZoneId.systemDefault());
        NewEventDto input = NewEventDto.builder()
                .itemName("")
                .fromTime(LocalDateTime.of(2023, 9, 22, 8, 25))
                .toTime(LocalDateTime.of(2023, 9, 22, 8, 45))
                .build();

        List<String> result = NewEventDtoValidator.validate(input, clock);

        assertThat(result).hasSize(1)
                .contains("Item name must be set");

    }

    @DisplayName("check if item name contain only valid strings for cases")
    @ParameterizedTest(name="when 'name' is -{0}-")
    @ValueSource(strings = {"    ", "\t", "\n"})
    @NullSource
    @EmptySource
    void shouldCheckThatItemNameIsValidString(String name) {
        clock = Clock.fixed(Instant.parse("2022-09-29T16:00:00Z"), ZoneId.systemDefault());
        NewEventDto input = NewEventDto.builder()
                .itemName(name)
                .fromTime(LocalDateTime.of(2023, 9, 22, 8, 25))
                .toTime(LocalDateTime.of(2023, 9, 22, 8, 45))
                .build();

        List<String> result = NewEventDtoValidator.validate(input, clock);

        assertThat(result).hasSize(1)
                .contains("Item name must be set");

    }
}