package ru.stqa.javaCourse.sandbox;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceMethod(Point p2){
        double distanceX = this.x - p2.getX();
        double distanceY = this.y - p2.getY();
        return Math.sqrt((distanceX*distanceX) + (distanceY*distanceY));
    }
}
