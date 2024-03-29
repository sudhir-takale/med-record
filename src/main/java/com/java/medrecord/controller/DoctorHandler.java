package com.java.medrecord.controller;

//import java.util.List;
import com.java.medrecord.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.medrecord.services.RegistrationServices;

@RestController
@RequestMapping(value = "/doctor", method = RequestMethod.GET)
public class DoctorHandler {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    RegistrationServices registrationServices;

    @GetMapping("/dashboard")
    public String home() {
        return "doctor/dashboard1";
    }

    @GetMapping("/appointments")
    public String showAppointmentsPage(Model model) {

        // List<Appointment> appointments=appointmentServices.getAllAppointments();

        // model.addAttribute("appointments", appointments);

        return "doctor/appointment";
    }

    // @PutMapping("/edit-appointment1/{id}")
    // public ResponseEntity<Appointment> updateAppointment(
    // @PathVariable int id,
    // @RequestParam String newDate,
    // @RequestParam String newTime
    // ) {
    // Appointment updatedAppointment = appointmentServices.updateAppointment(id,
    // newDate, newTime);
    // if (updatedAppointment != null) {
    // return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    // } else {
    // System.out.println("Resource not found");
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //
    // }
    // }

    @GetMapping("/edit-appointment/{id}")
    public String showUpdateAppointmentPage(@PathVariable int id, Model model) {
        model.addAttribute("appointmentid", id);
        return "doctor/editAppointment";
    }

    // go to the doctor registration page
    @GetMapping("/signup")
    public String register() {
        return "doctor/registration";

    }

    // Store the data of registered doctors
    @PostMapping("/signup1")
    public String registerDataStore(@RequestParam Long doctorid, @RequestParam String doctorName,
            @RequestParam String phoneNo, @RequestParam String email, @RequestParam String password,
            @RequestParam String speciality, @RequestParam String gender, @RequestParam String bloodGroup,
            @RequestParam int age, @RequestParam String address) {
        registrationServices.saveDoctorData(doctorid, doctorName, phoneNo, email, password, speciality, gender,
                bloodGroup, age, address);

        return "redirect:/doctor/dashboard";
    }

}
