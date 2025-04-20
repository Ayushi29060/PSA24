package com.exampleapi.service;

import com.exampleapi.entity.Registrations;
import com.exampleapi.exception.ResourceNotFound;
import com.exampleapi.payload.RegistrationDto;
import com.exampleapi.repository.RegistrationsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private RegistrationsRepository registrationsRepository;
    private ModelMapper modelMapper;
    public RegistrationService(RegistrationsRepository registrationsRepository, ModelMapper modelMapper) {
        this.registrationsRepository = registrationsRepository;
        this.modelMapper = modelMapper;

    }

    public RegistrationDto saveRegistration(RegistrationDto registrationDto) {
        //convert to Entity from Dto
      Registrations registration = convertDtoToEntity(registrationDto);
      registration.setId(1L);
        Registrations savedReg = registrationsRepository.save(registration);

        // Convert Entity to dto
        RegistrationDto savedRegDto = convertEntityToDto(savedReg);
        return savedRegDto;
    }

    public void deleteRegistration(Long id) {

        registrationsRepository.deleteById(id);
    }

    public void updateRegistration(Long id, Registrations registration) {
        //Actual record fetched from Database and updated with new values
        Optional<Registrations> opReg = registrationsRepository.findById(id);
        Registrations reg = opReg.get();
        reg.setName(registration.getName());
        reg.setEmail(registration.getEmail());
        reg.setMobile(registration.getMobile());
        registrationsRepository.save(reg);
    }

    public List<RegistrationDto> getAllRegistrations(
            int pageNo,
            int pageSize,
            String SortBy,
            String sortDir
    )

    {
          //use ternary operator for creating sort object
        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(SortBy).ascending() : Sort.by(SortBy).descending();  // sorting order based on provided direction
        //String x = condition?val1:val2;
        //creating page request with page number, size and sort
        Pageable page =  PageRequest.of(pageNo, pageSize, sort);
        Page<Registrations> records = registrationsRepository.findAll(page);
        List<Registrations> registrations = records.getContent();
        List<RegistrationDto> registrationDtos = registrations.stream().map(r -> convertEntityToDto(r)).collect(Collectors.toList());


        return registrationDtos;
    }

    public Registrations getRegistrationById(Long id) {
        // Fetching the record from database and returning it as an entity.
        Registrations registrations = registrationsRepository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFound("Record not found")

            );
        return registrations;
    }

    //convert dto to entity
    Registrations convertDtoToEntity(
            RegistrationDto registrationDto
    ){
        Registrations registrations = modelMapper.map(registrationDto, Registrations.class);
        return registrations;
    }
    //convert entity to dto
    RegistrationDto convertEntityToDto(
            Registrations registrations
    ){
        RegistrationDto registrationDto = modelMapper.map(registrations, RegistrationDto.class);
        return registrationDto;
    }
}


