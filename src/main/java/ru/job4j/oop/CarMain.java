package ru.job4j.oop;

public class CarMain {
    public static void main(String[] args) {
        Car1 car1 = new Car1("Марка", "Модель");
        Car1.Transmission transmission = car1.new Transmission();
        Car1.Brakes brakes = car1.new Brakes();
        car1.startEngine();
        transmission.accelerate();
        brakes.brake();
        Car1.TripComputer tripComputer = car1.new TripComputer();
        tripComputer.getInfo();
    }
}
