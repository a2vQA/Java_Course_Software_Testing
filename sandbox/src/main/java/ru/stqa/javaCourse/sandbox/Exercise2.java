package ru.stqa.javaCourse.sandbox;

import static java.lang.String.format;

public class Exercise2 {

    public static void main(String[] args) {
        Point p1 = new Point(-1.0, 4.0);
        Point p2 = new Point(6.0, -2.0);
        System.out.println(distance(p1, p2));
    }
    
    public static String distance(Point p1, Point p2){
        double distanceX = p2.x - p1.x;
        double distanceY = p2.y - p1.y;
        double distance =  Math.sqrt((distanceX*distanceX) + (distanceY*distanceY));
        return format("Расстояние между точкой на координате (%s;%s) и точкой (%s;%s) = %f",
                p1.x, p1.y, p2.x, p2.y, distance);
    }
}
