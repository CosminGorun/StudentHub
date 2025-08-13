import { Component } from '@angular/core';
import {UserService} from "../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main',
  imports: [],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {
   user:any=null;

   constructor(private userService:UserService,private router:Router) {
   }

    ngOnInit() {
     this.user=this.userService.getUserData();
    }
}
