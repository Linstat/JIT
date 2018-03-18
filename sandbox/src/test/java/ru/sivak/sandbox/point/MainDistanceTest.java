package ru.sivak.sandbox.point;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainDistanceTest {
    @Test

    public void testArea() {
        Point p1 = new Point(5,5);
        Point p2 = new Point(5,6);
        Assert.assertEquals(Distance.distance(p1,p2),1.0);
    }
}
