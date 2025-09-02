package ro.studenthub.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpoonacularService {

    private final RestTemplate restTemplate;
    @Value(value = "${api_key_spoonacular}")
    private String apiKey;
    private final String spoonacularUrl="https://api.spoonacular.com/";
    public SpoonacularService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String getRecipesByIngredient(String ingredients) {
        String url=spoonacularUrl+"recipes/findByIngredients?" +
                                   "number=10" +
                                    "&ingredients="+ingredients+
                                     "&apiKey="+apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    public String getPopularRecipes() {
        String url=spoonacularUrl+"recipes/complexSearch?" +
                "sort=popularity" +
                "&addRecipeInformation=true"+
                "&number=10" +
                "&apiKey="+apiKey;
        return restTemplate.getForObject(url, String.class);
    }
    public String getSearchedRecipes(String search) {
        String url=spoonacularUrl+"recipes/complexSearch?" +
                "query=" + search +
                "&sort=spoonacularScore" +
                "&sortDirection=desc"+
                "&addRecipeInformation=true"+
                "&number=10" +
                "&apiKey="+apiKey;
        return restTemplate.getForObject(url, String.class);
    }
}
