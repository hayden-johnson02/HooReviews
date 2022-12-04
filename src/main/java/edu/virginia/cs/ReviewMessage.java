package edu.virginia.cs;

public class ReviewMessage {
    private String message;
    private int score;

    public ReviewMessage(String message, int score) {
        this.message = message;
        this.score = score;
    }

    public String getMessage() {
        return message;
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
