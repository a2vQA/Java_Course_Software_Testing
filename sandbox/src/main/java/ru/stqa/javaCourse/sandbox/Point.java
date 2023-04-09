package ru.stqa.javaCourse.sandbox;

import static java.lang.String.format;

public class Point {

    public double x1;
    public double y1;
    public double x2;
    public double y2;

    public Point(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public String distance(){
        double distanceX = x2 - x1;
        double distanceY = y2 - y1;
        double distance =  Math.sqrt((distanceX*distanceX) + (distanceY*distanceY));
        return format("Расстояние между точкой на координате (%s;%s) и точкой (%s;%s) = %f",
                x1, y1, x2, y2, distance);
    }
}
