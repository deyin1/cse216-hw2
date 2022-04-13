package geometry;

import java.util.Comparator;

public class Counterclockwise implements Comparator<Point> {
    private Point center;
    public Counterclockwise(Point p){
        center = p;
    }

    public Counterclockwise(){

    }

    public int compare(Point a, Point b){
        double a1 = (Math.toDegrees(Math.atan2(a.getX() - center.getX(), a.getY() - center.getY())) + 360) % 360;
        double a2 = (Math.toDegrees(Math.atan2(b.getX() - center.getX(), b.getY() - center.getY())) + 360) % 360;
        return (int) (a2 - a1);
    }

}
