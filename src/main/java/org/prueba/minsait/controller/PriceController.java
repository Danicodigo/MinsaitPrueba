package org.prueba.minsait.controller;

import org.prueba.minsait.model.Price;
import org.prueba.minsait.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controlador REST para manejar las solicitudes relacionadas con los precios.
 *
 * @author <your_name_here>
 */
@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    /**
     * Constructor de la clase PriceController.
     *
     * @param priceService el servicio de precios que se va a utilizar.
     */
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * Método para obtener el precio de un producto en una fecha dada.
     *
     * @param date la fecha en la que se quiere buscar el precio. Formato esperado: yyyy-MM-dd'T'HH:mm:ss.
     * @param productId el ID del producto para el que se quiere buscar el precio.
     * @param brandId el ID de la marca para la que se quiere buscar el precio.
     * @return un objeto ResponseEntity que contiene el precio encontrado o un error 404 si no se encontró.
     */
    @GetMapping
    public ResponseEntity<Price> getPrice(@RequestParam("date") String date, @RequestParam("productId") Long productId, @RequestParam("brandId") Integer brandId) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        Price price = priceService.findPrice(String.valueOf(dateTime), productId, brandId);
        if (price != null) {
            return ResponseEntity.ok(price);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
