package com.coffeemaker.coffeeManager;

import com.coffeemaker.CoffeeRecipes.RecipesModel;
import com.coffeemaker.coffeeIngredients.CoffeeIngredientsModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

@RestController
public class CoffeeManagerController {

    @Value("${man.prop.one}")
    private String manPropertyOne;

    @Value("${man.prop.two}")
    private String manPropertyTwo;

    @Value("${man.prop.three}")
    private String manPropertyThree;

    @GetMapping("/coffeeschedule/showConfig")
    public String showConfig(){
        return "Property 1 = " + manPropertyOne + "<br/>" +
                "Property 2 = " + manPropertyTwo + "<br/>" +
                "Property 3 = " + manPropertyThree;
    }


    @Autowired
    private RestTemplateBuilder restBuilder;
    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    private String recipesService = "recipes-service";
    private String ingredientsService = "ingredients-service";


    @GetMapping("/")
    public String homeNew(){
        return "Hello from Manager";
    }

    //region get All
    @GetMapping("/ingredients")
    public String allIngredients(){

        String baseUrl = getServiceUrl(ingredientsService);
        RestTemplate restTemplate = restBuilder.build();
        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/api/ingredients" , HttpMethod.GET, null, String.class);

        return baseUrl + "<hr>" + response.getBody();
    }

    @GetMapping("/recipes")
    public RecipesModel[] allRecipes(){

        RestTemplate restTemplate = restBuilder.build();
        RecipesModel[] response = GetAll(recipesService,"/api/recipes", RecipesModel[].class);

        return response;
    }

    @GetMapping("/coffeeschedule")
    public HashMap<Integer, CoffeeManagerModel> getComponents(){
        return CoffeeManagerService.coffeeTimeList();
    }

    //endregion

    //region get single

    @GetMapping("/coffeeschedule/{id}")
    public String getSchedule(@PathVariable Integer id){

        CoffeeManagerModel manager = CoffeeManagerService.getCoffeeTime(id);
        String m = getRecipe(manager.getIdCoffee());

        return  "Coffee time: " + manager.getTime() + "<br/>" +
                "One time Coffee: " + manager.getIsOneTimeCoffee() + "<br/>" +
                //"Coffee id: " + manager.getIdCoffee() +"<br/>" +
                "Coffee Week: " + CoffeeManagerService.WeekByteToStr(manager.getWeek()) + "<hr>"+
                m;
    }

    @GetMapping("/recipes/{id}")
    public String getRecipe(@PathVariable Integer id){
        ResponseEntity<String> response = GetSingle(recipesService, "/api/recipes/" + id.toString());
        return response.getBody();
    }

    @GetMapping("/ingredients/{name}")
    public String getIngredient(@PathVariable String name){
        ResponseEntity<String> response = GetSingle(ingredientsService, "/api/ingredients/" + name);
        return response.getBody();
    }

    //endregion

    //region Add

    @PostMapping(path = "/coffeeschedule/add")
    public int createSchedule(@RequestBody CoffeeManagerModel coffeeTime){
        return CoffeeManagerService.createCoffeeTime(coffeeTime);
    }

    @PostMapping(path = "/recipes/add")
    public Integer createRecipe(@RequestBody RecipesModel recipe){
        return PostAdd(recipesService, "/api/recipes/add", RecipesModel.class, recipe);
    }

    @PostMapping(path = "/ingredients/update")
    public String updIngredients(@RequestBody Integer[] ingredients){

        String baseUrl = getServiceUrl(ingredientsService);
        RestTemplate restTemplate = restBuilder.build();

        RequestEntity<Integer[]> request = null;
        try {
            request = RequestEntity.post(new URI(baseUrl+ "/api/ingredients/update")).accept(MediaType.APPLICATION_JSON).body(ingredients);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return response.getBody();
    }

    //endregion

    //region Remove

    @PostMapping(path = "/coffeeschedule/rm")
    public String removeCoffee(@RequestBody Integer id){
        CoffeeManagerService.removeCoffeeTime(id);
        return "Successful";
    }

    @PostMapping(path = "/recipes/rm")
    public String  rmRecipe(@RequestBody Integer id){
            return PostRm(recipesService, "/api/recipes/rm", id);
    }

    //endregion

    //region Queries
    private ResponseEntity<String> GetSingle(String service, String path) {

        String baseUrl = getServiceUrl(service);
        RestTemplate restTemplate = restBuilder.build();
        ResponseEntity<String> response = restTemplate.exchange(baseUrl+path, HttpMethod.GET, null,
                String.class);
        return response;
    }

    private <T> T GetAll(String service, String path, Class<T> aClass) {
        String baseUrl = getServiceUrl(service);

        RestTemplate restTemplate = restBuilder.build();
        return restTemplate.exchange(baseUrl+path, HttpMethod.GET, null, aClass).getBody();
    }


    private <T> Integer PostAdd(String service, String path, Class<T> aClass, T object) {

        String baseUrl = getServiceUrl(service);
        RestTemplate restTemplate = restBuilder.build();

        RequestEntity<T> request = null;
        try {
            request = RequestEntity.post(new URI(baseUrl+path)).accept(MediaType.APPLICATION_JSON).body(object);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        ResponseEntity<Integer> response = restTemplate.exchange(request, Integer.class);
        return response.getBody();
    }

    private String PostRm(String service, String path, Integer id) {

        String baseUrl = getServiceUrl(service);
        RestTemplate restTemplate = restBuilder.build();

        RequestEntity<Integer> request = null;
        try {
            request = RequestEntity.post(new URI(baseUrl+path)).accept(MediaType.APPLICATION_JSON).body(id);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return response.getBody();
    }


    private String getServiceUrl(String serviceName) {
        InstanceInfo info = eurekaClient.getNextServerFromEureka(serviceName, false);
        return info.getHomePageUrl();
    }
    //endregion
}
