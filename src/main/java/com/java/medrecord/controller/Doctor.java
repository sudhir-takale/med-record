package com.java.medrecord.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.medrecord.dao.AppointmentRepositery;
import com.java.medrecord.entity.Appointment;
import com.java.medrecord.services.AppointmentServices;

@Controller
@RequestMapping(value = "/doctor", method = RequestMethod.GET)
public class Doctor {

	@Autowired
	AppointmentServices appointmentServices;
	
	@Autowired
	AppointmentRepositery appointmentRepositery;
	
	@GetMapping("/dashboard")
    public String home() {
        return "doctor/dashboard1";
    }

    @GetMapping("/signup")
    public String register() {
        return "doctor/registration";

    }
    
    @GetMapping("/appointments")
    public String showAppointmentsPage(Model model) {
        
    	List<Appointment> appointments=appointmentServices.getAllAppointments();
        
        model.addAttribute("appointments", appointments);

        return "doctor/appointment";
    }
    
    @PutMapping("/edit-appointment1/{id}")
    public ResponseEntity<Appointment> updateAppointment(
        @PathVariable int id,
        @RequestParam String newDate,
        @RequestParam String newTime
    ) {
        Appointment updatedAppointment = appointmentServices.updateAppointment(id, newDate, newTime);
        if (updatedAppointment != null) {
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        } else {
        	System.out.println("Resource not found");
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
    
    @GetMapping("/edit-appointment/{id}")
    public String showUpdateAppointmentPage(@PathVariable int id, Model model) {
        model.addAttribute("appointmentid", id);
        return "doctor/editAppointment";
    }

    
    
}











