package model;

public class Lesson {
    public static final String[] LESSON_TYPES = {"Yoga", "Zumba", "Spinning", "Pilates", "Crossfit"};

    private String type;
    private int day;
    private int customers;
    private double price;
    private double ratings;

    public Lesson(String type, int day, int customers, double price, double ratings) {
        this.type = type;
        this.day = day;
        this.customers = customers;
        this.price = price;
        this.ratings = ratings;
    }

    public String getType() {
        return type;
    }

    public int getDay() {
        return day;
    }

    public int getCustomers() {
        return customers;
    }

    public double getPrice() {
        return price;
    }

    public double getRatings() {
        return ratings;
    }
}

