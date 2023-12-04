package de.kraemer.jan.carService;

import de.kraemer.jan.carService.entity.Car;
import de.kraemer.jan.carService.repository.CarRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
@EnableDiscoveryClient
public class CarServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CarServiceApplication.class, args);
  }

  @Bean
  protected ApplicationRunner init(CarRepository carRepository) {
    carRepository.deleteAll();
    return args -> {
      Stream.of(
              "Ferrari",
              "Jaguar",
              "Porsche",
              "Lamborghini",
              "Bugatti",
              "AMC Gremlin",
              "Triumph Stag",
              "Ford Pinto",
              "Yugo GV")
          .forEach(name -> carRepository.save(Car.builder().name(name).build()));
      carRepository.findAll().forEach(System.out::println);
    };
  }
}
