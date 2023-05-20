package com.zara.price.infra.outputport;

import com.zara.price.domain.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface EntityRepository extends JpaRepository<Price, Long> {

    List<Price> findByBrandIdAndProductId(Integer brandId, Integer productId);

    @Query("select p from Price p where p.startDate <= :dateApply and p.endDate >= :dateApply and p.brandId = :brandId and p.productId = :productId")
    List<Price> getPriceByBrandIdProducIdDateApply(@Param("dateApply") LocalDateTime dateApply, @Param("brandId") Integer brandId, @Param("productId") Integer productId);

}
