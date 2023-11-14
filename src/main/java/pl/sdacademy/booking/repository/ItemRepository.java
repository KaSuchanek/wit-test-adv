package pl.sdacademy.booking.repository;

import org.springframework.data.repository.ListCrudRepository;
import pl.sdacademy.booking.data.ItemEntity;

public interface ItemRepository extends ListCrudRepository<ItemEntity, Long> {

    Long findItemByName(String name);
}