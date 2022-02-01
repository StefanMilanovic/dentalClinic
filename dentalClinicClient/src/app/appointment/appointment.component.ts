import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { UserService } from '../service/user.service';
import { User } from '../model/User';
import { Appointment } from '../model/Appointment';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit {

  appointment: Appointment;
  appointmentForm: FormGroup;
  submited = false;
  success = false;
  minDate: Date;
  maxDate: Date;
  retDate: Date;

  constructor(private userService: UserService, private formBuilder: FormBuilder) { 
    this.appointment = new Appointment();
    const currentYear = new Date().getFullYear();
    this.minDate = new Date();
    this.maxDate = new Date(currentYear, 11, 31);
    this.retDate = new Date();

  }

  ngOnInit() {
    this.appointmentForm=this.formBuilder.group({
      email: ['',Validators.required],
      number: ['',Validators.required], 
      time: ['',Validators.required],
      date: ['',Validators.required],
      duration: ['',Validators.required],
    });
  }

  onSubmit() {
    // alert("Submit!")
    this.submited = true;
    if (this.appointmentForm.invalid) {
      alert('Error! Try again.');
      return;
    } else {
      this.success = true;
      this.retDate = this.appointmentForm.value.date; 
      let splitted =  (this.appointmentForm.value.time).split(":",2);
      this.retDate.setHours(splitted[0], splitted[1], 0);
      this.appointmentForm.value.date= this.retDate; 
      console.log(this.retDate);
      this.appointment.email = this.appointmentForm.value.email;
      this.appointment.number = this.appointmentForm.value.number;
      this.appointment.code = "00000000"; //nule za pacijente, za zubare cemo imati neke razlicite brojeve
      this.appointment.role = "patient";
      this.appointment.date = this.appointmentForm.value.date;
      this.appointment.time = this.appointmentForm.value.time;
      this.appointment.duration = this.appointmentForm.value.duration;

      this.userService.addAppointment(this.appointment).subscribe((data: any) => {

        alert(data.text);
      });
    }
  }

}
