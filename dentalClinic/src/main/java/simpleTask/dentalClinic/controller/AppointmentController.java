package simpleTask.dentalClinic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import simpleTask.dentalClinic.model.Appointment;
import simpleTask.dentalClinic.model.User;
import simpleTask.dentalClinic.request.CancelAppointment;
import simpleTask.dentalClinic.request.Code;
import simpleTask.dentalClinic.request.MakeAppointment;
import simpleTask.dentalClinic.response.Result;
import simpleTask.dentalClinic.service.AppointmentService;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/appointment")
public class AppointmentController {

   
    @Autowired
    private AppointmentService appoitmentService;
    
    @Autowired
    private JavaMailSender jms;

    // rezervacija termina
    @RequestMapping(value="/makeAppointment",
    		method = RequestMethod.POST,
    		consumes = "application/json",
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Result> addAppointment(@RequestBody MakeAppointment makeAppointment){
    	 Result retval = new Result();
    	try{
    		User user = new User(1L, makeAppointment.getEmail(), makeAppointment.getNumber(), makeAppointment.getCode(), makeAppointment.getRole());  		
    		Date date = new Date();    		
    		date = makeAppointment.getDate();
    		//date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1); 
    		//user = appoitmentService.findUserByNumber(makeAppointment.getNumber());
    		
	        Appointment  appointment = new Appointment(1L, user, date, makeAppointment.getDuration(), "General examination at the dentist", false );     
	       
	        retval.setText(appoitmentService.save(appointment));
	        if(retval.getText().equals("Accepted")) {
		        //mejl za zubara
		        SimpleMailMessage dentalClinicMail = new SimpleMailMessage();
		        dentalClinicMail.setTo("DentalClinicSimpleTask2020@gmail.com");
		        dentalClinicMail.setSubject("DentalClinic Appointment");
		        dentalClinicMail.setReplyTo("DentalClinicSimpleTask2020@gmail.com");
		        dentalClinicMail.setText("Appointment is reserved at "+ appointment.getDate() + "\n\n" + "Patient number is: " + appointment.getUser().getNumber());
		        jms.send(dentalClinicMail);
		        
		        //mejl za klijenta
		        SimpleMailMessage patientMail = new SimpleMailMessage();
		        patientMail.setTo(appointment.getUser().getEmail());
		        patientMail.setSubject("DentalClinic Appointment");
		        patientMail.setReplyTo("DentalClinicSimpleTask2020@gmail.com");
		        patientMail.setText("You have successfully booked an appointment at " +  appointment.getDate() + "\n\n" + "DentalClinic");
		        jms.send(patientMail);
		        
		        return new ResponseEntity<Result>(retval, HttpStatus.OK);
	        }else {
	        	return new ResponseEntity<Result>(retval,HttpStatus.OK);
	        }
    	}catch (Exception e) {
    		
			e.printStackTrace();
			return new ResponseEntity<Result>(retval, HttpStatus.OK);
		}
    }

    	//otkazivanje temina
    	 @RequestMapping(value="/cancelAppointment",
    	    		method = RequestMethod.POST,
    	    		consumes = "application/json",
    	            produces = MediaType.APPLICATION_JSON_VALUE )
    	    public ResponseEntity<Result> deleteAppointment(@RequestBody CancelAppointment cancelAppointment){
    	    	 Result retval = new Result();
    	    	try{
	    	    		User user = new User(0L, cancelAppointment.getEmail(), cancelAppointment.getNumber(), cancelAppointment.getCode(), cancelAppointment.getRole());  		
	    	    	
	    	    		retval.setText(appoitmentService.deleteAppointment(user));
	    		        if(retval.getText().equals("Accepted")) {
	    		        //mejl za zubara
	    		        SimpleMailMessage dentalClinicMail = new SimpleMailMessage();
	    		        dentalClinicMail.setTo("DentalClinicSimpleTask2020@gmail.com");
	    		        dentalClinicMail.setSubject("DentalClinic Canceled Appointment");
	    		        dentalClinicMail.setReplyTo("DentalClinicSimpleTask2020@gmail.com");
	    		        dentalClinicMail.setText("Appointment is canceled. " + "\n\n" + "Patient number is: " + user.getNumber());
	    		        jms.send(dentalClinicMail);
	    		        
	    		        //mejl za klijenta
	    		        SimpleMailMessage patientMail = new SimpleMailMessage();
	    		        patientMail.setTo(user.getEmail());
	    		        patientMail.setSubject("DentalClinic Canceled Appointment");
	    		        patientMail.setReplyTo("DentalClinicSimpleTask2020@gmail.com");
	    		        patientMail.setText("You have successfully canceled an appointment" + "\n\n" + "DentalClinic");
	    		        jms.send(patientMail);
	    		        
	    		        return new ResponseEntity<Result>(retval, HttpStatus.OK);
    		        }else {
    		        	return new ResponseEntity<Result>(retval, HttpStatus.OK);
    		        }
    		        
    	    	}catch (Exception e) {
    	    		
    				e.printStackTrace();
    				return new ResponseEntity<Result>(retval, HttpStatus.OK);
    			}

    }


    	 @RequestMapping(value="/signin",
 	    		method = RequestMethod.POST,
 	    		consumes = "application/json",
 	            produces = MediaType.APPLICATION_JSON_VALUE )
 	    public ResponseEntity<Result> deleteAppointment(@RequestBody Code code){
 	    	 Result retval = new Result();
 	    	try{
 	    		User user = new User();
 	    		user = appoitmentService.findUserByCode(code.getNumber());	
 	    		if(user != null) {
 	    			retval.setText("Accepted");
    
	    		    return new ResponseEntity<Result>(retval, HttpStatus.OK);
 		        }else {
 		        	 retval.setText("Denied");
 		        	return new ResponseEntity<Result>(retval, HttpStatus.OK);
 		        }
 		        
 	    	}catch (Exception e) {
 	    		retval.setText("Denied");
 				e.printStackTrace();
 				return new ResponseEntity<Result>(retval, HttpStatus.OK);
 			}

    	 }
    	 
    	 @RequestMapping(value="/getAllAppointment",
  	    		method = RequestMethod.GET)
  	    public ResponseEntity<List<Appointment>> getAllAppointment(){
    		List<Appointment> retAppointmentList = new ArrayList<Appointment>();
  	    	try{	    	
  	    		retAppointmentList = appoitmentService.getAllAppointment();	
  	    		if(!retAppointmentList.isEmpty()) {

 	    		    return new ResponseEntity<List<Appointment>>(retAppointmentList, HttpStatus.OK);
  		        }else { 		        	
  		        	return new ResponseEntity<List<Appointment>>(retAppointmentList, HttpStatus.OK);
  		        }
  		        
  	    	}catch (Exception e) {
  	    		
  				e.printStackTrace();
  				return new ResponseEntity<List<Appointment>>(retAppointmentList, HttpStatus.OK);
  			}

  }
}
