import { Routes } from '@angular/router';
// import {CalendarComponent} from "./calendar/calendar.component";
import {LoginComponent} from "./login/login.component";
import {MainComponent} from "./main/main.component";
import {FoodComponent} from "./food/food.component";

export const routes: Routes = [
    {path:'',redirectTo:'login',pathMatch:'full'},
    // {path:'calendar',component: CalendarComponent},
    {path:'login',component: LoginComponent},
    {path:'main',component: MainComponent},
    {path:'food',component: FoodComponent},
];
