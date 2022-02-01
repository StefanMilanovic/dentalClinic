package simpleTask.dentalClinic.service;



import java.util.List;

import simpleTask.dentalClinic.model.Appointment;
import simpleTask.dentalClinic.model.User;

public interface AppointmentService {
	
	String save(Appointment appointment);
    
    String deleteAppointment(User user);
    
    User findUserByNumber(String number);
    
    User findUserByCode(String code);
    
    List<Appointment> getAllAppointment();
    
}
