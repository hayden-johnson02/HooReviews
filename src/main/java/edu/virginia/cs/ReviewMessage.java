package edu.virginia.cs;

public class ReviewMessage {
    private String message;
    String studentName;
    private int score;

    public ReviewMessage(String message, int score) {
        this.message = message;
        this.score = score;
    }
    public ReviewMessage(String message, int score, String studentName) {
        this.message = message;
        this.score = score;
        this.studentName = studentName;
    }

    public String getMessage() {
        return message;
    }

    public int getScore() {
        return score;
    }
}
