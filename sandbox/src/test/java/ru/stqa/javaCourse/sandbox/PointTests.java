package ru.stqa.javaCourse.sandbox;

import org.testng.annotations.Test;
import org.testng.Assert;

public class PointTests {

    @Test
    public void testDistanceMixed() {
        Point p1 = new Point(4.0, 6.0);
        Point p2 = new Point(-5.0, -1.0);
        Assert.assertEquals(p1.distanceMethod(p2), 11.401754);
    }

    @Test
    public void testDistanceNegative() {
        Point p1 = new Point(-2.0, -5.0);
        Point p2 = new Point(-5.0, -2.0);
        Assert.assertEquals(p1.distanceMethod(p2), 4.242641);
    }

    @Test
    public void testDistancePositive() {
        Point p1 = new Point(4.0, 6.0);
        Point p2 = new Point(6.0, 4.0);
        Assert.assertEquals(p1.distanceMethod(p2), 2.828427);
    }

    @Test
    public void testDistanceZero() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        Assert.assertEquals(p1.distanceMethod(p2), 0);
    }
}
