import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userData:any=null;

  setUserData(userData:any){
    this.userData=userData;
  }
  getUserData():any{
    return this.userData;
  }
}
