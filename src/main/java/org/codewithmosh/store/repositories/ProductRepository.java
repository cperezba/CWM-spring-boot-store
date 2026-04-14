package org.codewithmosh.store.repositories;

import org.codewithmosh.store.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
