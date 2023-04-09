package ru.stqa.javaCourse.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance(){
        Point mixedPoints = new Point(-1.0, 4.0, 6.0, -2.0);
        Assert.assertEquals(mixedPoints.distance(), "Расстояние между точкой на координате (-1.0;4.0) и" +
                " точкой (6.0;-2.0) = 9,219544");

        Point negativePoints = new Point(-15.0, -13.0, -13.0, -15.0);
        Assert.assertEquals(negativePoints.distance(), "Расстояние между точкой на координате (-15.0;-13.0)" +
                " и точкой (-13.0;-15.0) = 2,828427");

        Point positivePoints = new Point(125.0, 123.0, 123.0, 125.0);
        Assert.assertEquals(positivePoints.distance(), "Расстояние между точкой на координате (125.0;123.0)" +
                " и точкой (123.0;125.0) = 2,828427");

        Point zeroPoints = new Point(0, 0, 0, 0);
        Assert.assertEquals(zeroPoints.distance(), "Расстояние между точкой на координате (0.0;0.0) и" +
                " точкой (0.0;0.0) = 0,000000");
    }
}
