package org.codewithmosh.store.repositories;

import org.codewithmosh.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
