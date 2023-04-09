package ru.stqa.javaCourse.sandbox;

public class Exercise2 {

    public static double distance(Point p1, Point p2){
        double distanceX = p2.x - p1.x;
        double distanceY = p2.y - p1.y;
        return Math.sqrt((distanceX*distanceX) + (distanceY*distanceY));
    }
}
