package org.prueba.minsait.repository;

import org.prueba.minsait.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.brandId = :brandId AND p.productId = :productId AND p.startDate <= :date AND p.endDate >= :date ORDER BY p.priority DESC")
    Price findPrice(@Param("date") LocalDateTime date, @Param("productId") Long productId, @Param("brandId") Integer brandId);
}
