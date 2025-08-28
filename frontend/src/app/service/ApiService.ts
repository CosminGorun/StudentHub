import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";


@Injectable()
export class ApiService{
    private apiUrl="http://localhost:8080";
    constructor(private http:HttpClient) {}

    getAllUsers():Observable<any>{
        return this.http.get(`${this.apiUrl}/users/findAll`);
    }

    sendEmailValidationCode(email:string):Observable<any>{
        const mail={
            to:email,
            subject:"",
            body:""
        }
        return this.http.post(`${this.apiUrl}/mail/sendCode`,mail);
    }
    sendVerificationCode(email:string,code:string):Observable<any>{
        const body={
            email:email,
            code:code
        }
        return this.http.post(`${this.apiUrl}/mail/validateCode`,body);
    }
}