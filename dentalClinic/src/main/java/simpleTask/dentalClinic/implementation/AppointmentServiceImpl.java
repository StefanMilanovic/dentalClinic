package simpleTask.dentalClinic.implementation;


import simpleTask.dentalClinic.model.Appointment;
import simpleTask.dentalClinic.model.User;
import simpleTask.dentalClinic.service.AppointmentService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

@Service
public class AppointmentServiceImpl  implements AppointmentService {

	private ArrayList<Appointment> appointmentList;
	private ArrayList<User> userList;
	
	 @PostConstruct
	 private void init() {
		 appointmentList = new ArrayList<Appointment>();
		 userList = new ArrayList<User>();
		 
		 User user = new User(1L, "0652123123", "milanovicstefann@gmail.com", "12345678", "dentist");
		 userList.add(user);
		 
	 }
	
    @Override
    public String save(Appointment appointment) {
    	for(Appointment a : appointmentList) {
			if((a.getUser().getNumber().equals(appointment.getUser().getNumber()))) {//ako prethodno postoji termin za taj broj onda nemoj dodati
									
					return "Denied";
			}
			//Proveravamo da li se termin poklapa
			//odredimo kada postjeci termini pocinju i zavrsavaju
			Date startDateExist = new Date();
			Date endDateSetExist = new Date();
			Calendar cal = Calendar.getInstance();
			startDateExist = a.getDate();
			//odredimo kada zavrsi
			cal.setTime(startDateExist);
			cal.add(Calendar.MINUTE, Integer.parseInt(a.getDuration()));
			endDateSetExist = cal.getTime();
			
			//odredimo kada novi termin pocinje i zavrsava
			Date startDateNew = new Date();
			Date endDateNew = new Date();
			startDateNew = appointment.getDate();
			Calendar cal2 = Calendar.getInstance();
			//odredimo kada zavrsi
			cal2.setTime(startDateNew);
			cal2.add(Calendar.MINUTE, Integer.parseInt(appointment.getDuration()));
			endDateNew = cal2.getTime();
			if(startDateExist.before(endDateNew) && endDateSetExist.after(startDateNew) ) {
				System.out.println("\n There is existing appointment at selected time! Set new time or date.");
				return "Denied"; // ako postojeci termin pocinje pre kraja novog uz to ako postojeci zavrsava posle pocetka novog nece imati presek termini 
			}
    	}
    	appointmentList.add(appointment);
    	return "Accepted";
    }

	@Override
	public User findUserByNumber(String number) {
		User user = new User();
		
		for(User u : userList) {//broj telefona je jedinstven atribut za jednog korisnika
			if(u.getNumber().equals(number)) {
				user = u;
				return user;
			}
		}
		return null;
	}

	@Override
	public String deleteAppointment(User user) {
		if(user.getRole().equals("dentist")){// ako je dosao sa stranice zubara odma mozemo da ga otkazemo bez ostalih provera
			for(User u: userList) {
				if(u.getCode().equals(user.getCode())) {
					for(Appointment a : appointmentList) {
						if(a.getUser().getNumber().equals(user.getNumber())) {
							appointmentList.remove(a);
							return "Accepted";
						}
					}
				}
			}
			return "Denied";	
		}else {
		
		for(Appointment a : appointmentList) {
			if(a.getUser().getNumber().equals(user.getNumber())) { // otkazivanje termina na osnovu broja
				Date tomorrow = getTomorrow();				
				if(a.getDate().after(tomorrow)) { // Ako je posle sutrasnjeg(24h) datuma moze otkazati 
					appointmentList.remove(a);
					return "Accepted";
				}else{
					return "Denied. You cannot cancel 24 hours before the appointment. "; // promeniti poruku a na frontu u zavisnosti od iste ispisati tekst posle..
				}
				
			}
		}
		return "Denied";	
		}
	}
    
    
	private Date getTomorrow() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, +1);
	    return cal.getTime();
	}

	@Override
	public User findUserByCode(String code) {
		User user = new User();
		
		for(User u : userList) {//broj telefona je jedinstven atribut za jednog korisnika
			if(u.getCode().equals(code)) {
				user = u;
				return user;
			}
		}
		return null;
	}
	
	@Override
	public List<Appointment> getAllAppointment() {
		 List<Appointment> retAppointmentList = new ArrayList<Appointment>();
	        for (Appointment a : appointmentList) {
	        	retAppointmentList.add(a);
	        }
	        return retAppointmentList;
	}
}
