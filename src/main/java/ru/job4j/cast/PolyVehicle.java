package ru.job4j.cast;

public class PolyVehicle {
    public static void main(String[] args) {
        Vehicle airplane = new Airplane();
        Vehicle warPlane = new Airplane();
        Vehicle train = new Train();
        Vehicle electricTrain = new Train();
        Vehicle speedTrain = new Train();
        Vehicle bus = new Bus();
        Vehicle shuttleBus = new Bus();
        Vehicle intercityBus = new Bus();
        Vehicle doubleDecker = new Bus();

        Vehicle[] vehicles = new Vehicle[]{airplane, warPlane, train,
                electricTrain,
                speedTrain,
                bus,
                shuttleBus,
                intercityBus,
                doubleDecker};
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
        }
    }
}
