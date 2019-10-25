package com.cars.front.controller;

import com.cars.front.model.Car;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Api(description = "CRUD controller for displaying cars from API with thymeleaf")
@Controller
public class FrontController {

    private final String apiAddress = "http://localhost:8075";

    // Inject via application.properties
    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    private RestTemplate restTemplate = new RestTemplate();

    @ApiOperation(value = "page d'acceuil")
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @ApiOperation(value = "afficher la liste de toutes les voitures")
    @RequestMapping(value = {"/cars"}, method = RequestMethod.GET)
    public String carList(Model model) {
        Car[] cars = restTemplate.getForObject(apiAddress + "/cars", Car[].class);
        model.addAttribute("cars", cars);
        return "carList";
    }

    @ApiOperation(value = "afficher une voiture")
    @RequestMapping(value = {"/car/{id}"}, method = RequestMethod.GET)
    public String carById(Model model, @PathVariable int id) {
//        System.out.println(apiAddress + "/car/" + id);
//        Car car = new Car(id, "Audi", "A2");
        Car car = restTemplate.getForObject(apiAddress + "/car/" + id, Car.class);
        model.addAttribute("car", car);
        return "car";
    }

    @ApiOperation(value = "ajouter une voiture")
    @RequestMapping(value = {"/addCar"}, method = RequestMethod.POST)
    public String carSave(Model model) {
        Car[] cars = restTemplate.getForObject(apiAddress + "/cars", Car[].class);
        model.addAttribute("cars", cars);
        return "addCar";
    }

    @ApiOperation(value = "modifier une voiture")
    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.PUT)
    public String carUpdateById(Model model, @PathVariable int id) {
        Car[] cars = restTemplate.getForObject(apiAddress + "/cars", Car[].class);
        model.addAttribute("cars", cars);
        return "carList";
    }

    @ApiOperation(value = "enlever une voiture (par son id) de la liste")
    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.DELETE)
    public String carDeleteById(Model model, @PathVariable int id) {
        Car car = restTemplate.getForObject(apiAddress + "/car/delete/" + id, Car.class);
        Car[] cars = restTemplate.getForObject(apiAddress + "/cars", Car[].class);
        model.addAttribute("cars", cars);
        return "carList";
    }
}
