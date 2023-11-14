package pl.sdacademy.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdacademy.booking.model.EventDto;
import pl.sdacademy.booking.service.EventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;


    @GetMapping("/events")
    public List<EventDto> findAll(){
        return eventService.findEvents();
    }


}