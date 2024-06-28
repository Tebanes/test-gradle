package org.adr.testgradle;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpPersonService {

    private final ExpPersonRepository expPersonRepository;

    public ExpPersonService(ExpPersonRepository expPersonRepository) {
        this.expPersonRepository = expPersonRepository;
    }

    public List<ExpPerson> findAll() {
        return expPersonRepository.findAll();
    }
}
