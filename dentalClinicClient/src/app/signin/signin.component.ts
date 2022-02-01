import { Component, OnInit } from '@angular/core';
import { Code } from '../model/Code';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  code: Code;
  signinForm: FormGroup;
  submited = false;
  success = false;
  constructor(private userService: UserService, private formBuilder: FormBuilder, private router:Router) { 
    this.code = new Code();
  }

  ngOnInit() {
    this.signinForm=this.formBuilder.group({
      number: ['',Validators.required],

    });
  }

  onSubmit() {
    // alert("Submit!")
    this.submited = true;
    if (this.signinForm.invalid) {
      alert('Error! Try again.');
      return;
    } else {
      this.success = true;

      this.code.number = this.signinForm.value.number;
 
      this.userService.signin(this.code).subscribe((data: any) => {
        alert(data.text);
        console.log(data);
        if(data.text == "Accepted"){
          localStorage.setItem('code', this.code.number);
          this.router.navigate(['/schedule']);
        }
      });
    }
  }

}
