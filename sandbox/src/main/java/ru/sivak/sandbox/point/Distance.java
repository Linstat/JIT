package ru.sivak.sandbox.point;

public class Distance {

    public static void main(String[] args) {
        Point p1 = new Point (5,5);
        Point p2 = new Point (10,3);
        System.out.println("Вызов из main "+ distance(p1,p2));
        System.out.println("Вызов из объекта " + p2.sayDistanceTo(p1));
    }

    public static double distance(Point p1, Point p2){
        double dis = Math.sqrt(Math.pow((p1.x - p2.x),2) + Math.pow((p1.y - p2.y),2));
        return dis;
    }

}
