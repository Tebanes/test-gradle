package org.adr.testgradle;

import org.springframework.data.repository.ListCrudRepository;


public interface ExpPersonRepository extends ListCrudRepository<ExpPerson, Long> {
}
