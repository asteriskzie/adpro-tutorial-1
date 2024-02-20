package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import jakarta.annotation.PostConstruct;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model) {        
        Product product = service.getById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProductPost(@PathVariable String id, @ModelAttribute Product product, Model model) {
        service.edit(id, product);
        return "redirect:../list";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id, Model model) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

@Controller 
@RequestMapping("/car")
class CarController extends ProductController{
    private static final String REDIRECT_LISTCAR = "redirect:listCar";

    @Autowired 
    private CarServiceImpl carService; 

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carService.create(car); 
        return REDIRECT_LISTCAR;
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        List<Car> allCars = carService.findAll(); 
        model.addAttribute("cars", allCars); 
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carService.findById(carId); 
        model.addAttribute("car", car); 
        return "editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        carService.update(car.getCarId(), car); 

        return REDIRECT_LISTCAR;
    }

    @PostMapping("/deleteCar") 
    public String deleteCar(@RequestParam("carId") String carId) {
        carService.deleteCarById(carId);
        return REDIRECT_LISTCAR;
    }

}