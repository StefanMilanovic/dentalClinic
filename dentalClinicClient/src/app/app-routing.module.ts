import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppointmentComponent } from './appointment/appointment.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { CancelAppointmentComponent } from './cancel-appointment/cancel-appointment.component';
import { SigninComponent } from './signin/signin.component';

const routes: Routes = [
  
  {path:'appointment', component: AppointmentComponent},
  {path:'schedule', component: ScheduleComponent}, 
  {path:'cancel-appointment', component: CancelAppointmentComponent},
  {path:'signin', component: SigninComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
