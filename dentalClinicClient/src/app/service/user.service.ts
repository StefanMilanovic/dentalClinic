import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { User } from '../model/User';
import { Appointment } from '../model/Appointment';
import { CancelAppointment } from '../model/CancelAppointment';
import { Code } from '../model/Code';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }


  addAppointment(appointment: Appointment ){
    console.log(appointment);
    return  this.httpClient.post('http://localhost:8080/appointment/makeAppointment', appointment );
  }

  deleteAppointment(cancelAppointment: CancelAppointment ){
    console.log(cancelAppointment);
    return  this.httpClient.post('http://localhost:8080/appointment/cancelAppointment', cancelAppointment );
  }
  
  signin(code: Code ){
    console.log(code);
    return  this.httpClient.post('http://localhost:8080/appointment/signin', code );
  }

  getAllAppointment(){
    console.log("Loading appointment...");
    return  this.httpClient.get('http://localhost:8080/appointment/getAllAppointment');
  }
}
