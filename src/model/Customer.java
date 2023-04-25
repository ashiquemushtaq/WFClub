package model;

public class Customer {
    private final String name;
    private final String lessonName;
    private final String day;

    public Customer(String name, String lessonName, String day) {
        this.name = name;
        this.lessonName = lessonName;
        this.day = day;
    }

	public String getName() {
		return name;
	}

	public String getLessonName() {
		return lessonName;
	}

	public String getDay() {
		return day;
	}

   
}
