package pl.sdacademy.booking.mapper;

import lombok.experimental.UtilityClass;
import pl.sdacademy.booking.data.ItemAttributeEntity;
import pl.sdacademy.booking.data.ItemEntity;
import pl.sdacademy.booking.model.ItemDto;

import java.util.HashSet;
import java.util.Set;

// mappery statyczne opisujemy jako UtilityClass by nie bylo watpliwosci.
//UtilityClass powoduje, ze pojawia sie prywatny konstruktor
@UtilityClass
public class ItemDtoMapper {

    public static ItemDto map(ItemEntity entity) {
        return ItemDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                // po rezygnacji z hinernate trzeba jakos to obsluzyc - do zrobienia
//                .attributes(attributes)
                .build();
    }

    private static Set<String> mapAttributes(Set<ItemAttributeEntity> itemAttributeEntities) {
        Set<String> result = new HashSet<>();
        for (ItemAttributeEntity attributeEntity : itemAttributeEntities) {
            result.add(attributeEntity.getAttributeName());
        }
        return result;
    }
}
