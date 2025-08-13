import { Component } from '@angular/core';
import {Router, RouterLink, RouterOutlet} from "@angular/router";
import {NgIf} from "@angular/common";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-login',
  imports: [
    // RouterLink,
    // RouterOutlet,
    NgIf
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
    loginTypes:string[]=['username','mail','phone'];
    curentType:number=0;
    validMail:boolean=false;
    validPhone:boolean=false;
    actions:string[]=['login','createAccount'];
    action:number=0;

    validNewMail:boolean=false;
    validNewPhone:boolean=false;

    constructor(private userService:UserService,private router:Router){}


    nextType(){
        this.curentType=(this.curentType+1)%this.loginTypes.length;
    }
    prevType(){
        if(this.curentType==0){
            this.curentType=this.loginTypes.length-1;
        }else{
            this.curentType=(this.curentType-1);
        }
    }
     validateEmail(){
        this.validMail=true;
     }
     validatePhone(){
        this.validPhone=true;
     }
    validateNewEmail(){
        this.validNewMail=true;
    }
    validateNewPhone(){
        this.validNewPhone=true;
    }
     swichAction(){
        this.action=(this.action+1)%this.actions.length;
     }
     login(){
        const testUser={
            username:"ion",
            role:"admin"
        }
        this.userService.setUserData(testUser);
        this.router.navigate(['main']);
     }
}

