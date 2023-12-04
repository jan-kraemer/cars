package de.kraemer.jan.apiGateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
public class CarController {
  private final Logger log = LoggerFactory.getLogger(CarController.class);

  private final WebClient.Builder webClientBuilder;
  private final ReactiveCircuitBreaker reactiveCircuitBreaker;

  public CarController(
      WebClient.Builder webClientBuilder,
      ReactiveCircuitBreakerFactory<?, ?> reactiveCircuitBreakerFactory) {
    this.webClientBuilder = webClientBuilder;
    this.reactiveCircuitBreaker = reactiveCircuitBreakerFactory.create("circuit-breaker");
  }

  record Car(String name) {}

  @GetMapping("cool-cars")
  public Flux<Car> coolCars() {
    return reactiveCircuitBreaker.run(
        webClientBuilder
            .build()
            .get()
            .uri("http://car-service/cars")
            .retrieve()
            .bodyToFlux(Car.class),
            throwable -> {
              log.warn("Error making request to car service", throwable);
              return Flux.empty();
            });
  }
}
