package ru.sivak.sandbox.point;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointDistanceTest {
    @Test

    public void testArea() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(3,4);
        Assert.assertEquals(p1.sayDistanceTo(p2),2.8284271247461903);
        Assert.assertEquals(p2.sayDistanceTo(p1),2.8284271247461903);
        Assert.assertEquals(p2.sayDistanceTo(p1),Math.sqrt(Math.pow((p1.x - p2.x),2) + Math.pow((p1.y - p2.y),2)));
    }

}
