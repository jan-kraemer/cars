package de.kraemer.jan.carService.controller;

import de.kraemer.jan.carService.entity.Car;
import de.kraemer.jan.carService.repository.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CarController {

  private final CarRepository carRepository;

  public CarController(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  @GetMapping("/cars")
  public Collection<Car> allCars() {
    return carRepository.findAll();
  }
}
