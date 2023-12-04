package de.kraemer.jan.carService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class Car {

  public Car() {}

  public Car(String name) {
    this.name = name;
  }

  @Id @GeneratedValue private Long id;

  @NotNull private String name;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static CarBuilder builder() {
    return new CarBuilder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Car car = (Car) o;
    return Objects.equals(getId(), car.getId()) && Objects.equals(getName(), car.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName());
  }

  @Override
  public String toString() {
    return "Car{" + "id=" + id + ", name='" + name + '\'' + '}';
  }

  public static class CarBuilder {
    private String name;


    public CarBuilder name(String name) {
      setName(name);
      return this;
    }

    public Car build() {
      return new Car(name);
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
