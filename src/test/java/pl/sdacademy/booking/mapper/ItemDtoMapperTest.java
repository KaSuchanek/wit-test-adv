package pl.sdacademy.booking.mapper;

import org.junit.jupiter.api.Test;
import pl.sdacademy.booking.data.ItemAttributeEntity;
import pl.sdacademy.booking.data.ItemEntity;
import pl.sdacademy.booking.model.ItemDto;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ItemDtoMapperTest {

    @Test
    void shouldMapEmptyEntityToDto() {
        ItemEntity input = new ItemEntity();
        // na razie nie ma tego elementu
//        input.setAttributes(Set.of());

        ItemDto result = ItemDtoMapper.map(input);

//        assertThat(result).isNotNull()
//                .hasFieldOrPropertyWithValue("id", 0)
//                .hasFieldOrPropertyWithValue("name", null);
        assertThat(result).isNotNull()
                .isEqualTo(ItemDto.builder()
                        .id(null)
                        .price(null)
                        .description(null)
                        .name(null)
                        .attributes(null)
                        // .attributes(Set.of())
                        .build()
                );

    }

    @Test
    void shouldMapNotEmptyEntityToDto() {
        ItemEntity input = new ItemEntity();
        input.setPrice(BigDecimal.TEN);
        input.setName("itemName");
        input.setId(100L);
        input.setDescription("itemDesc");
        ItemAttributeEntity attributeEntity = new ItemAttributeEntity();
        attributeEntity.setAttributeName("1");
        ItemAttributeEntity attributeEntity2 = new ItemAttributeEntity();
        attributeEntity2.setAttributeName("2");
//        input.setAttributes(Set.of(attributeEntity, attributeEntity2));

        ItemDto result = ItemDtoMapper.map(input);

//        assertThat(result).isNotNull()
//                .hasFieldOrPropertyWithValue("id", 0)
//                .hasFieldOrPropertyWithValue("name", null);
        assertThat(result).isNotNull()
                .isEqualTo(ItemDto.builder()
                        .id(100L)
                        .price(BigDecimal.TEN)
                        .description("itemDesc")
                        .name("itemName")
                                .attributes(null)
//                        .attributes(Set.of("1", "2"))
                        .build()
                );

    }
}