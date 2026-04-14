package org.codewithmosh.store.repositories;

import org.codewithmosh.store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
