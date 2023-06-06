package org.prueba.minsait.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.prueba.minsait.errors.InvalidDateException;
import org.prueba.minsait.errors.PriceNotFoundException;
import org.prueba.minsait.model.Price;
import org.prueba.minsait.repository.PriceRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class PriceServiceTest {

    private PriceRepository priceRepository;
    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        priceRepository = Mockito.mock(PriceRepository.class);
        priceService = new PriceService(priceRepository);
    }

    @Test
    void testFindPrice_success() {
        Price expectedPrice = new Price();
        when(priceRepository.findPrice(any(), anyLong(), any())).thenReturn(expectedPrice);

        Price result = priceService.findPrice("2023-06-05T08:00:00", 1L, 1);
        assertEquals(expectedPrice, result);
    }

    @Test
    void testFindPrice_priceNotFound() {
        when(priceRepository.findPrice(any(), anyLong(), any())).thenReturn(null);

        assertThrows(PriceNotFoundException.class, () -> {
            priceService.findPrice("2023-06-05T08:00:00", 1L, 1);
        });
    }

    @Test
    void testFindPrice_invalidDateFormat() {
        assertThrows(InvalidDateException.class, () -> {
            priceService.findPrice("Invalid Date", 1L, 1);
        });
    }

}
