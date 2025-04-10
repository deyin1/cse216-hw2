package geometry;

import java.util.Collection;
import java.util.List;

/**
 * This class is given to you as an outline for testing your code. You can modify this as you want, but please keep in
 * mind that the lines already provided here as expected to work exactly as they are.
 *
 * @author Ritwik Banerjee
 */
public class GeometryTest {
    
    public static void main(String... args) {
        testTriangleSymmetries();
        testSquareSymmetries();
//        sqTest();
    }
    
    private static void testTriangleSymmetries() {
        // t0 doesn't form a equilateral triangle. the following line is expected to throw an IllegalArgumentException
        try{
            EqTriangle t0 = new EqTriangle(new Point(1, 1), new Point(1, 2), new Point(1, 3));
        }
        catch(IllegalArgumentException e){
            System.out.println("Not a valid equilateral triangle!");
        }
        
        EqTriangle t1 = new EqTriangle(new Point(0, 0), new Point(2, 0),
                                       new Point(1, Math.sin(Math.toRadians(60)) * 2));

        EqTriangle t2 = t1.rotateBy(10);
        EqTriangle t3 = t1.rotateBy(120);

        System.out.println(t2.toString());
        System.out.println(t3.toString());
        
        TriangleSymmetries triangleSymmetries = new TriangleSymmetries();
        System.out.println(triangleSymmetries.areSymmetric(t1, t2)); // expected to return false
        System.out.println(triangleSymmetries.areSymmetric(t1, t3)); // expected to return true
        Collection<EqTriangle> symmetries = triangleSymmetries.symmetriesOf(t1);
        
        // Your code must ensure that t1.toString() returns the following String
        // "EqTriangle: (0, 0), (2, 0), (1, 1.732)"
        // Any non-integer coordinate value must be correctly rounded and represented with three decimal places.
        for (EqTriangle t : symmetries)
            System.out.println(t.toString());
    }
    
    private static void testSquareSymmetries() {
        Square s1 = new Square(new Point(1, 1), new Point(1, 2), new Point(0, 2), new Point(0, 1));
        Square s2 = s1.rotateBy(30);
        Square s3 = s1.rotateBy(180);

        System.out.println(s2.toString());
        System.out.println(s3.toString());
        
        SquareSymmetries squareSymmetries = new SquareSymmetries();
        System.out.println(squareSymmetries.areSymmetric(s1, s2)); // expected to return false
        System.out.println(squareSymmetries.areSymmetric(s1, s3)); // expected to return true
        Collection<Square> symmetries = squareSymmetries.symmetriesOf(s1);
        
        // Your code must ensure that s1.toString() returns the following String
        // "Square: (0, 1), (1, 1), (1, 2), (0, 2)"
        // Note that the order of vertices is not what was given in the constructor above, but rather, the
        // counterclockwise ordering.
        // Any non-integer coordinate value must be correctly rounded and represented with three decimal places.
        for (Square s : symmetries)
            System.out.println(s.toString());
    }

    private static void sqTest() {
        Square s1 = new Square(new Point(1, 1), new Point(1, 2), new Point(0, 2), new Point(0, 1));
        Square s2 = s1.rotateBy(10);
        System.out.println(s2);
    }
}
