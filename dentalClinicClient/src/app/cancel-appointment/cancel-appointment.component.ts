import { Component, OnInit } from '@angular/core';
import { CancelAppointment } from '../model/CancelAppointment';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-cancel-appointment',
  templateUrl: './cancel-appointment.component.html',
  styleUrls: ['./cancel-appointment.component.css']
})
export class CancelAppointmentComponent implements OnInit {

  cancelAppointment: CancelAppointment;
  cancelAppointmentForm: FormGroup;
  submited = false;
  success = false;
  constructor(private userService: UserService, private formBuilder: FormBuilder) { 
    this.cancelAppointment = new CancelAppointment();
  }

  ngOnInit() {
    this.cancelAppointmentForm=this.formBuilder.group({
      email: ['',Validators.required],
      number: ['',Validators.required],

    });
  }

  onSubmit() {
    // alert("Submit!")
    this.submited = true;
    if (this.cancelAppointmentForm.invalid) {
      alert('Error! Try again.');
      return;
    } else {
      this.success = true;

      this.cancelAppointment.email = this.cancelAppointmentForm.value.email;
      this.cancelAppointment.number = this.cancelAppointmentForm.value.number;
      this.cancelAppointment.code = "00000000"; //nedefinisan za pacijente, za zubare cemo imati neke razlicite brojeve
      this.cancelAppointment.role = "patient";
      
      
      this.userService.deleteAppointment(this.cancelAppointment).subscribe((data: any) => {
        alert(data.text);
        console.log(data);
      });
    }
  }

}
