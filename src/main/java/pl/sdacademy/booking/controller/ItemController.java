package pl.sdacademy.booking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdacademy.booking.model.ItemDto;
import pl.sdacademy.booking.service.ItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/items")
    public List<ItemDto> presentCatalog() {
        return itemService.findItems();
    }
}
