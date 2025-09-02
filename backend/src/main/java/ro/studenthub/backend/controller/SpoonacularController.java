package ro.studenthub.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.studenthub.backend.service.SpoonacularService;

@RestController
@RequestMapping("/spoonacular")
@CrossOrigin(origins = "http://localhost:4200")
public class SpoonacularController {

    private final SpoonacularService spoonacularService;

    public SpoonacularController(SpoonacularService spoonacularService) {
        this.spoonacularService = spoonacularService;
    }

    @GetMapping("/findByIngredinets")
    public String findByIngredinets(@RequestParam("ingredients") String ingredients) {
        return spoonacularService.getRecipesByIngredient(ingredients);
    }

    @GetMapping("/getPopularRecipes")
    public String getPopularRecipes() {
        return spoonacularService.getPopularRecipes();
    }

    @GetMapping("/getSearchRecipes")
    public String getSearchRecipes(@RequestParam("text") String text) {
        return spoonacularService.getSearchedRecipes(text);
    }
}
