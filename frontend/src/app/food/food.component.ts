import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import { FormsModule } from "@angular/forms";
import { ApiService } from "../service/ApiService";
import {HttpClientModule} from "@angular/common/http";
import {parseJson} from "@angular/cli/src/utilities/json-file";
import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-food',
  standalone: true,
    imports: [
        RouterLink,
        HttpClientModule,
        FormsModule,
        NgIf,
        NgForOf,
        NgOptimizedImage
    ],
  providers:[ApiService],
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.css']
})
export class FoodComponent implements OnInit {
  prompt: string = '';
  list: any = null;  // tip generic
  myRecipes:boolean=false;
  searchRecipe:boolean=false;
  constructor(private router: Router, private apiService: ApiService) {}

  popularRecipe:any=null;
  searchedRecipes:any=null;
  searchText:string='';

  ngOnInit() {
      this.apiService.getPopularRecipe()
          .subscribe({
            next: data => {
              this.popularRecipe=data;
            },error: err => {
              console.log(err);
            }
          })
  }


  sendPrompt() {
    const userPrompt = "romana:" + this.prompt;
    this.apiService.sendPromptToAi(userPrompt)
        .subscribe({
          next: (response) => {
            // Dacă response e string, parsează, altfel folosește direct
            this.list = response;

          },
          error: (err) => {
            console.error('Eroare la trimiterea promptului', err);
          }
        });
  }
  searchRecipes(){
      this.searchRecipe=true;
      this.apiService.getRecipesByTitle(this.searchText)
          .subscribe({
              next: data => {
                  this.searchedRecipes=data;
              },error: err => {
                  console.log(err);
              }
          })
  }
}
