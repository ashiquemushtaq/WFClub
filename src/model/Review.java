package model;

public class ReviewLesson {
    private final String lesson;
    private final int rating;

    public ReviewLesson(String lesson, int rating) {
        this.lesson = lesson;
        this.rating = rating;
    }

    public String getLesson() {
        return lesson;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Lesson: " + lesson + ", Rating: " + rating;
    }
}
