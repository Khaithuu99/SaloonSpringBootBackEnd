package com.example.MySaloonSpringboot.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.MySaloonSpringboot.Model.Appointment;
import com.example.MySaloonSpringboot.Repository.AppointmentRepository;
import com.example.MySaloonSpringboot.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/appointment")
public class AppointmentController {
@Autowired

 private AppointmentRepository appointmentRepository;

    //Get all Orders
    @GetMapping("/")
    public List<Appointment> getAllOrders()
    {
     return appointmentRepository.findAll();
    }

    //post order
    @PostMapping("/")
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // update order
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable long id, @RequestBody Appointment appointment) {
        Appointment ord = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
        ord.setName(appointment.getName());
        ord.setDescription(appointment.getDescription());
        ord.setAppointmentDate(appointment.getAppointmentDate());

        Appointment ord2 = appointmentRepository.save(ord);
        return ResponseEntity.ok(ord2);
    }

    // get customer by id
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable long id) {
        Appointment ord = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
        return ResponseEntity.ok(ord);
    }
    
    // delete customer
    @DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Long id){
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order not exist with id :" + id));
		
		appointmentRepository.delete(appointment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
}
