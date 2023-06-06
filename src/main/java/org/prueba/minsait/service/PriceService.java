package org.prueba.minsait.service;

import org.prueba.minsait.errors.InvalidDateException;
import org.prueba.minsait.errors.PriceNotFoundException;
import org.prueba.minsait.model.Price;
import org.prueba.minsait.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Servicio para gestionar las operaciones relacionadas con los precios.
 * 
 * @author <your_name_here>
 */
@Service
public class PriceService {

    private final PriceRepository priceRepository;

    /**
     * Constructor de la clase PriceService.
     * 
     * @param priceRepository el repositorio de precios que se va a utilizar.
     */
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Método para buscar un precio por fecha, ID de producto y ID de marca.
     * 
     * @param date la fecha en la que se quiere buscar el precio. Formato esperado: yyyy-MM-dd'T'HH:mm:ss.
     * @param productId el ID del producto para el que se quiere buscar el precio.
     * @param brandId el ID de la marca para la que se quiere buscar el precio.
     * @return el precio encontrado.
     * @throws InvalidDateException si el formato de la fecha es inválido.
     * @throws PriceNotFoundException si no se encuentra un precio para la fecha, ID de producto y ID de marca dados.
     */
    public Price findPrice(String date, Long productId, Integer brandId) {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid date format: " + date);
        }
        Price price = priceRepository.findPrice(dateTime, productId, brandId);
        if (price == null) {
            throw new PriceNotFoundException("Price not found for the given date, product ID and brand ID.");
        }
        return price;
    }
}
