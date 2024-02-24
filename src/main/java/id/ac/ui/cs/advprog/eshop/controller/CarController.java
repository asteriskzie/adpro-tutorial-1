package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService; 

    @GetMapping("/list") 
    public ModelAndView carListPage() {
        ModelAndView modelAndView = new ModelAndView("carList");
        List<Car> allCars = carService.findAll(); 
        modelAndView.addObject("cars", allCars); 
        return modelAndView;
    }

    private static final String REDIRECT_LISTCAR = "redirect:list";


    @GetMapping("/create")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/create")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carService.create(car); 
        return REDIRECT_LISTCAR;
    }

    // @GetMapping("/list")
    // public String carListPage(Model model) {
    //     List<Car> allCars = carService.findAll(); 
    //     model.addAttribute("cars", allCars); 
    //     return "car";
    // }

    @GetMapping("/edit/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carService.findById(carId); 
        model.addAttribute("car", car); 
        return "editCar";
    }

    @PostMapping("/edit")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        carService.update(car.getCarId(), car); 

        return REDIRECT_LISTCAR;
    }

    @PostMapping("/delete") 
    public String deleteCar(@RequestParam("carId") String carId) {
        carService.deleteCarById(carId);
        return REDIRECT_LISTCAR;
    }

}