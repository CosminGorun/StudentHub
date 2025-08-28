import { Component } from '@angular/core';
import {Router, RouterLink, RouterOutlet} from "@angular/router";
import {NgIf} from "@angular/common";
import {UserService} from "../service/user.service";
import {ApiService} from "../service/ApiService";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {EMPTY, switchMap} from "rxjs";

@Component({
  selector: 'app-login',
    imports: [
        // RouterLink,
        // RouterOutlet,
        HttpClientModule,
        NgIf,
        FormsModule
    ],
    providers:[ApiService],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {
    loginTypes:string[]=['username','mail','phone'];
    curentType:number=0;
    validMail:boolean=false;
    validPhone:boolean=false;
    actions:string[]=['login','createAccount','recoverPassword'];
    action:number=0;

    showSendCodeNewMail:boolean=false;
    validNewPhone:boolean=false;

    confirmedIdentidy:boolean=false;
    recoverMet:string='email';
    mailsended:boolean=false;
    phonesended:boolean=false;

    createAccountMail:string='';
    createAccountMailCode:string='';
    mailValMess:string='';
    validNewMail:boolean=false;

    password:string='';
    repassword:string='';

    username:string='';
    validNewUsername:boolean=true;

    mailAllredyExists:boolean=false;

    test:string='';

    loginUsername:string='';
    loginPassword:string='';
    wrontLogin:boolean=false;
    constructor(private userService:UserService,private router:Router,private apiService:ApiService){}


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
        this.showSendCodeNewMail=true;
    }
    validateNewPhone(){
        this.validNewPhone=true;
    }
    regex= /[0-9a-zA-Z]+@[a-zA-z]+.[a-z]+/;
    sendValidationEmailCode() {
        if (!this.regex.test(this.createAccountMail)) {
            this.mailValMess = "The current email is invalid";
            return;
        }

        this.apiService.verifyExistenMail(this.createAccountMail).pipe(
            switchMap((exists: boolean) => {
                if (exists) {
                    this.mailAllredyExists = true;
                    return EMPTY;
                } else {
                    this.mailAllredyExists = false;
                    return this.apiService.sendEmailValidationCode(this.createAccountMail);
                }
            })
        ).subscribe({
            next: (response) => {
                console.log('Email trimis cu succes', response);
                this.showSendCodeNewMail = true;
            },
            error: (err) => {
                console.error('Eroare la trimiterea email-ului', err);
            }
        });
    }

    sendVerificationCode(){
        if(!this.regex.test(this.createAccountMail)){
            this.mailValMess="the curent email is invalid";
            return;
        }
        if(this.createAccountMailCode.length!=6){
            this.mailValMess="codul este invalid";
            return;
        }
        this.apiService.sendVerificationCode(this.createAccountMail,this.createAccountMailCode)
            .subscribe({
                next: (response) => {
                    console.log('Email trimis cu succes', response);
                    if(response==true){
                        this.mailValMess="mail-ul a fost validat"
                        this.showSendCodeNewMail = false;
                        this.validNewMail=true;
                    }else{
                        this.mailValMess="cod gresit";
                    }
                },
                error: (err) => {
                    console.error('Eroare la trimiterea email-ului', err);
                }
            })
    }
    createUser(){
        this.apiService.saveUser(this.username,this.createAccountMail,this.password)
            .subscribe({
                next: (response) => {
                        if(response==true){
                            this.action=0;
                        }else{
                            this.mailValMess="erroare la creeere"
                        }
                },
                error: (err) => {
                    console.error('Eroare la trimiterea email-ului', err);
                }
            })
    }
    loginWithUsername(){
        this.apiService.loginWithUsername(this.loginUsername,this.loginPassword)
            .subscribe({
                next: (response) => {
                    if(response!=null){
                        const user={
                            id:response.id,
                            username:response.username,
                            phone:response.phone,
                            email:response.email
                        }
                        this.userService.setUserData(user);
                        this.router.navigate(['/main']);
                    }else{
                          this.wrontLogin = true;
                    }
                },
                error: (err) => {
                    console.error('Eroare la trimiterea email-ului', err);
                }
            })
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

