package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Едет");
    }

    @Override
    public void passengers(int amountPassengers) {
        System.out.println("Пассажиров " + amountPassengers);
    }

    @Override
    public int fillUp(int amountOfFuel) {
        int fuelPrice = 63;
        System.out.println("Заправились на " + amountOfFuel * fuelPrice);
        return amountOfFuel * fuelPrice;
    }

    public static void main(String[] args) {
        Transport bus = new Bus();
        bus.fillUp(100);
        bus.passengers(100);
        bus.drive();
    }
}
