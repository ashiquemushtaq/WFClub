package model;

public class Lesson {
    public static final String[] LESSON_TYPES = {
            "Yoga",
            "Zumba",
            "Spinning",
            "Pilates",
            "Crossfit"
    };

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

    // getters
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

    // setters
    public void setType(String type) {
        this.type = type;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "type='" + type + '\'' +
                ", day=" + day +
                ", customers=" + customers +
                ", price=" + price +
                ", ratings=" + ratings +
                '}';
    }

}