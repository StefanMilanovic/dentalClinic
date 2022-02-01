import { Component, OnInit } from '@angular/core';
import { CancelAppointment } from '../model/CancelAppointment';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
} from '@angular/core';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours,
  addMinutes,
} from 'date-fns';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { DataSource } from '@angular/cdk/table';

const colors: any = {
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent  implements OnInit{

  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;
  viewDate: Date = new Date();
  refresh: Subject<any> = new Subject();
  calendarEvent: CalendarEvent;
  events: CalendarEvent[] = [
  
    {
      start: addHours(startOfDay(new Date()), 2),
      end: addHours(new Date(), 2),
      title: 'Test event',
      color: colors.yellow,
    },
  ];

  activeDayIsOpen: boolean = true;



  //otkazivanje termina 
  cancelAppointment: CancelAppointment;
  cancelAppointmentForm: FormGroup;
  submited = false;
  success = false;
 
  constructor(private modal: NgbModal, private userService: UserService, private router:Router, private formBuilder: FormBuilder) {
    this.cancelAppointment = new CancelAppointment();
  }

  ngOnInit() {
    this.cancelAppointmentForm=this.formBuilder.group({
      code: ['',Validators.required],
      number: ['',Validators.required],

    });

    this.userService.getAllAppointment().subscribe((data: any) => {
      console.log(data); 
      console.log(this.events);
     
      for (let i = 0; i < data.length; i++) {
        //console.log(  startOfDay(addMinutes(new Date(data[i].date), 30)));
      this.events = [
        ...this.events,
        {
          title:"Patient phone number: "+ data[i].user.number + "  Type: " + data[i].type,
          start: new Date(data[i].date),
          end: addMinutes(new Date(data[i].date), data[i].duration),
          color: colors.blue,          
        },
      ];
    }

      this.router.navigate(['/schedule']);

    });
  }

  onSubmit() { // za otkazivanje 
    // alert("Submit!")
    this.submited = true;
    if (this.cancelAppointmentForm.invalid) {
      alert('Error! Try again.');
      return;
    } else {
      this.success = true;

      this.cancelAppointment.email = "milanovicstefann@gmail.com";// nije nam vazno u ovom slucaju proveravamo samo broj i kod
      this.cancelAppointment.number = this.cancelAppointmentForm.value.number;
      this.cancelAppointment.code = this.cancelAppointmentForm.value.code ;  // provericemo na beku ako ima na pocetku metode poklapanje sa kodom zubara iz liste odma otkazuj termin
      this.cancelAppointment.role = "dentist";
      
      
      this.userService.deleteAppointment(this.cancelAppointment).subscribe((data: any) => {
        alert(data.text);
        console.log(data);
      });
    }
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }
}