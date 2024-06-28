package org.adr.testgradle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class ExpPersonController {

    private final ExpPersonService personService;

    public ExpPersonController(ExpPersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<ExpPerson>> getAll(){
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/jelou")
    public String jelou(){
        return "jelou";
    }
}
