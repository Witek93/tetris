package tetris.model;

import java.math.BigInteger;

public class Score {

    private BigInteger points;
    private long lines;

    public Score() {
        this.points = new BigInteger("0");
        this.lines = 0;
    }

    public void addPoints(int amount) {
        this.points = this.points.add(new BigInteger(String.valueOf(amount)));
    }

    public String getPointsValue() {
        return this.points.toString();
    }

    public void incrementLines() {
        this.lines++;
    }

    public String getLinesValue() {
        return String.valueOf(this.lines);
    }

}
