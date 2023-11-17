package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public Error() {
    }

    public void printInfo() {
        System.out.println("Active: " + active);
        System.out.println("Error status: " + status);
        System.out.println("Error " + message);
    }

    public static void main(String[] args) {
        Error error = new Error(true, 500, "500: Internal Server Error");
        error.printInfo();
        Error error1 = new Error(true, 404, "404: Not Found");
        error1.printInfo();
        Error error2 = new Error(true, 429, "429: Too Many Requests");
        error2.printInfo();
        Error error3 = new Error(true, 503, "503: Service Unavailable");
        error3.printInfo();
        Error error4 = new Error();
        error4.printInfo();
    }
}
