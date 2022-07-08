package com.example.MySaloonSpringboot.Repository;

import com.example.MySaloonSpringboot.Model.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
    
}
