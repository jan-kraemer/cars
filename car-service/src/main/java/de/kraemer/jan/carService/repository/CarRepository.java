package de.kraemer.jan.carService.repository;

import de.kraemer.jan.carService.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {}
