package com.exampleapi.controller;

import com.exampleapi.entity.Registrations;
import com.exampleapi.payload.RegistrationDto;
import com.exampleapi.repository.RegistrationsRepository;
import com.exampleapi.service.RegistrationService;
import jakarta.servlet.Registration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@RestController
@RequestMapping("/api/v1/registrations")
public class RegistrationController {

            private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    //http://localhost:8080/api/v1/registrations

    @PostMapping
    public ResponseEntity<RegistrationDto> addRegistration(
            @RequestBody RegistrationDto registrationDto
    ){

       RegistrationDto r = registrationService.saveRegistration(registrationDto);
        return new ResponseEntity<>( r, HttpStatus.CREATED);
       }




    //http://localhost:8080/api/v1/registrations/record/{id}
    @DeleteMapping("/record/{id}")
    public String deleteRegistration(
            @PathVariable Long id  //This is the id of the registration that needs to be deleted
    ) {
        registrationService.deleteRegistration(id);
        return "deleted";
    }

    @PutMapping("{id}")
    public String updateRegistration(
            @PathVariable Long id,
            @RequestBody Registrations registration  //This is the registration that needs to be updated
    ) {
        registrationService.updateRegistration(id, registration);
        return "done";
    }

    //http://localhost:8080/api/v1/registrations?pageNo=0&pagesize=5&sortBy=name&sortDir=asc
    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "5", required = false) int pageSize, //This is the pagination parameters
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDir
    ) {
        List<RegistrationDto> dtos = registrationService.getAllRegistrations(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/registrations/id/{id}  //This is the endpoint to get a specific registration by its id
    @GetMapping("/id/{id}")
    public ResponseEntity<Registrations>tRegistrationById(
            @PathVariable Long id  //This is the id of the registration that needs to be fetched
    ) throws FileNotFoundException {
        FileReader fr = new FileReader("E://test.txt");
       Registrations registrations = registrationService.getRegistrationById(id);
       return new ResponseEntity<>(registrations,HttpStatus.OK);


    }
}
