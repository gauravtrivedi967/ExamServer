import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { defaultUrlMatcher } from '@angular/router';
import {UserService} from 'src/app/services/user.service';
import  Swal from 'sweetalert2';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{
  constructor(private userService:UserService, private snack:MatSnackBar){}

  public user={
    username:'',
    password:'',
    firstName:'',
    lastName:'',
    email:'',
    phone:'',
  };

  ngOnInit():void{}

  formSubmit(){
    console.log(this.user);
    if(this.user.username==''|| this.user.username==null)
    {
      //alert('User is required !!')
      this.snack.open('Username is required', 'ok',{
      duration: 3000,
      })
      return;
    }

    //validate

    //addUser: userservice 
    this.userService.addUser(this.user).subscribe(
      (data:any) => {
        //sucsess
        console.log(data);
        //alert("success");
        Swal.fire('Sucess','user id is ' +data.id,'success');
      },
      (error) => {
        //error
        console.log(error);
        //alert("something went wrong");
        this.snack.open('Something went wromg !!','ok',{
        duration: 3000
      })
      }
    );
  }

 //this.user
}
