package geometry;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.math.*;

/**
 * The class implementing equilateral triangles, i.e., triangles in which all three sides have the same length.
 * Note: you can add more methods if you want, but additional methods must be <code>private</code> or <code>protected</code>.
 *
 * @author Derek Yin
 */
public class EqTriangle implements Shape {
     private ArrayList<Point> verticesList;
    /**
     * The constructor accepts an array of <code>Point</code>s to form the vertices of the equilateral triangle. If more
     * than three points are provided, only the first three are considered by this constructor. If less than three
     * points are provided, or if the points do not form a valid equilateral triangle, the constructor throws
     * <code>java.lang.IllegalArgumentException</code>.
     *
     * @param vertices the array of vertices (i.e., <code>Point</code> instances) provided to the constructor.
     */
    public EqTriangle(Point... vertices) {
        if(!isMember(Arrays.asList(vertices)) || vertices.length < 3) throw new IllegalArgumentException();
        verticesList = new ArrayList<Point>();
        for (int i = 0; i < 3; i++) {
            Point p = vertices[i];
            double x = (double) Math.round(p.getX() * 1000) / 1000;
            double y = (double) Math.round(p.getY() * 1000) / 1000;
            Point newP = new Point(x, y);
            // System.out.println("(" + x + ", " + y + ")");
            verticesList.add(newP);

        }


    }
    
    /**
     * Checks if the series of <code>Point</code> instances form a valid equilateral triangle if first three form the
     * vertices of the triangle.
     *
     * @param vertices the list of specified vertices.
     * @return <code>true</code> if the first three vertices in the argument form a valid equilateral triangle, and
     * <code>false</code> otherwise.
     */
    @Override
    public boolean isMember(List<Point> vertices) {
//        for (Point p : vertices){
//            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
//        }
        double first = distance(vertices.get(0), vertices.get(1));
//        System.out.println("First:" + first);
        for (int i = 0; i < vertices.size(); i++){
            for (int j = i+1; j < vertices.size(); j++){
                //System.out.println(distance(vertices.get(i), vertices.get(j)));
                if (Math.abs(first - distance(vertices.get(i), vertices.get(j))) > 0.0001) return false;
            }
        }
        return true;
        }

    private double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }
    
    @Override
    public int numberOfSides() {
        return 3;
    }
    
    @Override
    public List<Point> vertices() {
        return this.verticesList;
    }

    private Point findCenter(){
        double x = 0.0;
        double y = 0.0;
        for (Point p : verticesList){
            x += p.getX();
            y += p.getY();
        }
        x = x / 3.0;
        y = y / 3.0;
        return new Point(x, y);
    }

    @Override
    public EqTriangle rotateBy(int degrees) {
        double radians = Math.toRadians(degrees);
        Point center = findCenter();
        ArrayList<Point> rotated = new ArrayList<Point>(3);
        for (Point p : verticesList){
            double tempX = p.getX()-center.getX();
            double tempY = p.getY()-center.getY();
            double x = (tempX * Math.cos(radians)) - (tempY * Math.sin(radians));
            double y = (tempX * Math.sin(radians)) + (tempY * Math.cos(radians));
            x += center.getX();
            y += center.getY();
           // y = (double)Math.round(y * 100000d) / 100000d;
            rotated.add(new Point(x, y));// ROUND ALL VALUES HERE.
        }
        return new EqTriangle(rotated.get(0), rotated.get(1), rotated.get(2));

    }
    @Override
    public String toString(){
        return "Triangle: " + verticesList.get(0) + ", " + verticesList.get(1) + ", " + verticesList.get(2);
    }
}
