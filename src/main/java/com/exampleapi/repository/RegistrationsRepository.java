package com.exampleapi.repository;

import com.exampleapi.entity.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationsRepository extends JpaRepository<Registrations, Long> {
}