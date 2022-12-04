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

    public String getStudent() {
        return studentName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
