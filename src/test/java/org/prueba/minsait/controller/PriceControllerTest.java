package org.prueba.minsait.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.prueba.minsait.controller.PriceController;
import org.prueba.minsait.model.Price;
import org.prueba.minsait.service.PriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class PriceControllerTest {

    private PriceService priceService;
    private PriceController priceController;

    @BeforeEach
    public void setUp() {
        priceService = Mockito.mock(PriceService.class);
        priceController = new PriceController(priceService);
    }

    @Test
    void testGetPrice_success() {
        Price expectedPrice = new Price();
        when(priceService.findPrice(anyString(), anyLong(), any())).thenReturn(expectedPrice);

        ResponseEntity<Price> response = priceController.getPrice("2023-06-05T08:00:00", 1L, 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice, response.getBody());
    }

    @Test
    void testGetPrice_notFound() {
        when(priceService.findPrice(anyString(), anyLong(), any())).thenReturn(null);

        ResponseEntity<Price> response = priceController.getPrice("2023-06-05T08:00:00", 1L, 1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
