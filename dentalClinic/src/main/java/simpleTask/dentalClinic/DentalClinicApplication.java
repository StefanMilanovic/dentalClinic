package simpleTask.dentalClinic;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import simpleTask.dentalClinic.model.Appointment;

@SpringBootApplication
public class DentalClinicApplication {

	private List<Appointment> appointmentList;
	
	public static void main(String[] args) {
		SpringApplication.run(DentalClinicApplication.class, args);
		
		Appointment appointment = new Appointment();
		appointment.setId(2);
		
		
	}

}
