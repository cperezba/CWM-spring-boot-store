package org.codewithmosh.store.repositories;

import org.codewithmosh.store.dtos.ProductSummary;
import org.codewithmosh.store.dtos.ProductSummaryDTO;
import org.codewithmosh.store.entities.Category;
import org.codewithmosh.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);


    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(BigDecimal newPrice, Byte categoryId);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    @Query("select new org.codewithmosh.store.dtos.ProductSummaryDTO(p.id, p.name) from Product p where p.category = :category")
    List<ProductSummaryDTO> findByCategory(@Param("category") Category category);

}
