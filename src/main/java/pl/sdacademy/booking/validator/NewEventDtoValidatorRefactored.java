package pl.sdacademy.booking.validator;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import pl.sdacademy.booking.model.NewEventDto;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class NewEventDtoValidatorRefactored {
    public static List<String> validate(NewEventDto newEventDto, Clock clock) {
        List<String> result = new ArrayList<>();

        if (newEventDto == null) {
            result.add("Event must be defined - got null");
            return result;
        }

        checkIfTimeIsNull(newEventDto, result);

        if (newEventDto.getFromTime() != null && newEventDto.getToTime() != null) {
            Duration duration = Duration.between(newEventDto.getFromTime(), newEventDto.getToTime());
            checkIfNotToBeforeFrom(duration, result);
            checkIfLastTooLong(duration, result);
            checkIfNotInThePast(newEventDto, clock, result);
            checkIfNotOutOfWorkingHours(newEventDto, result);

        }
        checkIfItemCorrect(newEventDto, result);

        return result;
    }

    private static void checkIfItemCorrect(NewEventDto newEventDto, List<String> result) {
        if (StringUtils.isBlank(newEventDto.getItemName())) {
            result.add("Item name must be set");
        }
    }

    private static void checkIfNotOutOfWorkingHours(NewEventDto newEventDto, List<String> result) {
        if (newEventDto.getFromTime().getHour() < 8
                || newEventDto.getFromTime().getHour() >= 16
                || newEventDto.getToTime().getHour() > 16
                || (newEventDto.getToTime().getHour() == 16 && newEventDto.getToTime().getMinute() > 0)) {
            // podanie warunkow poprawnych w komunikacie jest dobra praktyka
            String message = "Event cannot be set out of the working hours - from 08:00 to 16:00";
            result.add(message);
        }
    }

    private static void checkIfNotInThePast(NewEventDto newEventDto, Clock clock, List<String> result) {
        LocalDateTime timeNow = LocalDateTime.now(clock);
        if (newEventDto.getFromTime().isBefore(timeNow)) {
            String message = "Event cannot be set in the past";
            result.add(message);
        }
    }

    private static void checkIfLastTooLong(Duration duration, List<String> result) {
        if (duration.toMinutes() > 30) {
            result.add("Too long event");
        }
    }

    private static void checkIfNotToBeforeFrom(Duration duration, List<String> result) {
        if (duration.isNegative()) {
            result.add("To is before from");
        }
    }

    private static void checkIfTimeIsNull(NewEventDto newEventDto, List<String> result) {
        if (newEventDto.getFromTime() == null) {
            result.add("From is null");
        }
        if (newEventDto.getToTime() == null) {
            result.add("To is null");
        }
    }
}