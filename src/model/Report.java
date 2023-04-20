package model;

public class FitnessReportModel {
    private String type;
    private int attendees;
    private int price;

    public FitnessReportModel(String type, int attendees, int price) {
        this.type = type;
        this.attendees = attendees;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FitnessReportModel [type=" + type + ", attendees=" + attendees + ", price=" + price + "]";
    }
}
