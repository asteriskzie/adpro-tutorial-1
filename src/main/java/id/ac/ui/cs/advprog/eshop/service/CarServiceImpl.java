package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.UUID;


@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository; 
    private CarIdentifier carIdentifier;

    @Override
    public Car create(Car car) {
        if (car.getCarId() == null) {             
            carIdentifier.setCarId(car);
        }
        return carRepository.create(car);         
    }

    @Override 
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add); 
        return allCar; 
    }
    
    @Override 
    public Car findById(String carId) {
        return carRepository.findById(carId); 
    }

    @Override
    public void update(String carId, Car car) {
        carRepository.update(carId, car); 
    }

    @Override
    public void deleteCarById(String carId) {
        carRepository.delete(carId); 
    }
}

interface CarIdentifier {
    void setCarId(Car car);
}

class UUIDCarIdentifier implements CarIdentifier {
    @Override
    public void setCarId(Car car) {
        UUID uuid = UUID.randomUUID();
        car.setCarId(uuid.toString());
    }
}