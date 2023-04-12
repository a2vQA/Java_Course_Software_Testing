package ru.stqa.javaCourse.sandbox;

import static java.lang.String.format;

public class Exercise2 {

    public static void main(String[] args) {
        Point p1 = new Point(4.0, 6.0);
        Point p2 = new Point(-5.0, -1.0);
        double distanceFunction = distanceFunction(p1 , p2);
        double distanceMethod = p1.distanceMethod(p2);
        System.out.println(format("Расстояние между точкой на координате (%s;%s) и точкой (%s;%s) использую функцию %f",
                p1.x, p1.y, p2.x, p2.y, distanceFunction));
        System.out.println(format("Расстояние между точкой на координате (%s;%s) и точкой (%s;%s) используя метод %f",
                p1.x, p1.y, p2.x, p2.y, distanceMethod));
    }

    public static double distanceFunction(Point p1, Point p2){
        double distanceX = p2.x - p1.x;
        double distanceY = p2.y - p1.y;
        return Math.sqrt((distanceX*distanceX) + (distanceY*distanceY));
    }
}
