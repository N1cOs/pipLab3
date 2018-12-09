package ru.ifmo.se.lab3.domain;

import javafx.geometry.Point2D;

public class Parabola {

    private Point2D leftPoint;
    private Point2D rightPoint;
    private Point2D vertex;

    public Parabola(Point2D leftPoint, Point2D rightPoint, Point2D vertex) {
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
        this.vertex = vertex;
    }

    private double a;
    private double b;
    private double c;

    public void setCoefficients(double radius){
        double x1 = leftPoint.getX() * radius;
        double y1 = leftPoint.getY() * radius;

        double x2 = rightPoint.getX() * radius;
        double y2 = rightPoint.getY() * radius;

        double x3 = vertex.getX() * radius;
        double y3 = vertex.getY() * radius;

        a = (y3 - ((x3 * (y2 - y1) + x2 * y1 - x1 * y2) / (x2 - x1))) / (x3 * (x3 - x1 -x2) + x1 * x2);
        b = (y2 - y1) / (x2 - x1) - a * (x1 + x2);
        c = (x2 * y1 - x1 * y2) / (x2 - x1) + a * x1 * x2;
    }

    public double getValue(double x){
        return a * x * x + b * x + c;
    }
}
