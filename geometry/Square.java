package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The class impementing squares.
 * Note: you can add more methods if you want, but additional methods must be <code>private</code> or <code>protected</code>.
 *
 * @author {add your full name here}
 */
public class Square implements Shape {
    private ArrayList<Point> verticesList;
    /**
     * The constructor accepts an array of <code>Point</code>s to form the vertices of the square. If more than four
     * points are provided, only the first four are considered by this constructor. If less than four points are
     * provided, or if the points do not form a valid square, the constructor throws <code>java.lang.IllegalArgumentException</code>.
     *
     * @param vertices the array of vertices (i.e., <code>Point</code> instances) provided to the constructor.
     */
    public Square(Point... vertices) {
        if(!isMember(Arrays.asList(vertices)))throw new IllegalArgumentException();
        verticesList = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            Point p = vertices[i];
            double x = (double) Math.round(p.getX() * 1000) /1000;
            double y = (double) Math.round(p.getY() * 1000) / 1000;
            Point newP = new Point(x, y);
            // System.out.println("(" + x + ", " + y + ")");
            verticesList.add(newP);
        }
    }
    
    /**
     * Checks if the series of <code>Point</code> instances form a valid square if the first four form the vertices of
     * the square. This method considers the points in a counterclockwise manner starting with the vertex with the least
     * x-value. If multiple vertices have the same x-value, the counterclockwise ordering starts at the vertex with the
     * least y-value amongst them.
     *
     * @param vertices the list of specified vertices.
     * @return <code>true</code> if the first four vertices in the argument form a valid square, and <code>false</code>
     * otherwise.
     */
    @Override
    public boolean isMember(List<Point> vertices) {
//        for (Point p : vertices){
//            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
//        }
        Counterclockwise cc = new Counterclockwise(findCenter(vertices));
        Collections.sort(vertices, cc);
//        for (Point p : vertices){
//            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
//        }
        double s1 = distance(vertices.get(0), vertices.get(1));
        int i = 0, j = 1;
        while (j < vertices.size()){
            if (Math.abs(s1 - distance(vertices.get(i), vertices.get(j))) > 0.0001){
                return false;
            }
            else {
                i++;
                j++;
            }
        }
        return true;
    }

    private double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    @Override
    public int numberOfSides() {
        return 4;
    }
    
    @Override
    public List<Point> vertices() {
        return this.verticesList; // TODO
    }
    
    @Override
    public Square rotateBy(int degrees)  {
        double radians = Math.toRadians(degrees);
        Point center = findCenter(verticesList);
        ArrayList<Point> rotated = new ArrayList<Point>(4);
        for (Point p : verticesList){
            double tempX = p.getX()-center.getX();
            double tempY = p.getY()-center.getY();
            double x = (tempX * Math.cos(radians)) - (tempY * Math.sin(radians));
            double y = (tempX * Math.sin(radians)) + (tempY * Math.cos(radians));
            x += center.getX();
            y += center.getY();
            rotated.add(new Point(x, y));
        }
        return new Square(rotated.get(0), rotated.get(1), rotated.get(2), rotated.get(3));
    }

    @Override
    public String toString() {
        List<Point> temp = verticesList;
        Counterclockwise cc = new Counterclockwise(findCenter(temp));
        Collections.sort(temp, cc);
        return "Square :" + temp.get(1) + ", " + temp.get(2) + ", " + temp.get(3) + ", " + temp.get(0);
    }

    private Point findCenter(List<Point> vertices){
        double x = 0.0;
        double y = 0.0;
        for (Point p : vertices){
            x += p.getX();
            y += p.getY();
        }
        x = x / 4.0;
        y = y / 4.0;
        // System.out.println("Center =" + "(" + x + ", " + y + ")");
        return new Point(x, y);
    }
}
