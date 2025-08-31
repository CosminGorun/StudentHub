import {Component, OnInit} from '@angular/core';
import {UserService} from "../service/user.service";
import {Router, RouterLink} from "@angular/router";
import {ApiService} from "../service/ApiService";
import {NgForOf} from "@angular/common";
import {HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-main',
    imports: [
        HttpClientModule,
        RouterLink
    ],
    providers: [ApiService],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements OnInit {
   user:any=null;
   users:any=null;
   constructor(private userService:UserService ) {
   }

    ngOnInit() {
       this.user=this.userService.getUserData();
    }
}
