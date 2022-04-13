package geometry;

import java.util.ArrayList;
import java.util.*;

public class SquareSymmetries implements Symmetries<Square>{

    @Override
    public boolean areSymmetric(Square s1, Square s2) {
        Collection<Square> col = symmetriesOf(s1);
        List<Double> x = new ArrayList<>(4);
        List<Double> y = new ArrayList<>(4);
        for (Point p : s2.vertices()){
            x.add(p.getX());
            y.add(p.getY());
        }
        for (Square sq : col){
            List<Double> xs = new ArrayList<>(4);
            List<Double> ys = new ArrayList<>(4);
            for (Point p : sq.vertices()){
                xs.add(p.getX());
                ys.add(p.getY());
            }
            if (x.equals(xs) && y.equals(ys)) return true;
        }
        return false;
    }

    @Override
    public Collection<Square> symmetriesOf(Square square) {
        ArrayList<Square> lst = new ArrayList<>();
        lst.add(square);
        lst.add(square.rotateBy(90));
        lst.add(square.rotateBy(180));
        lst.add(square.rotateBy(270));
        lst.add(new Square(square.vertices().get(3), square.vertices().get(2), square.vertices().get(1), square.vertices().get(0)));
        lst.add(new Square(square.vertices().get(1), square.vertices().get(0), square.vertices().get(3), square.vertices().get(2)));
        lst.add(new Square(square.vertices().get(0), square.vertices().get(3), square.vertices().get(2), square.vertices().get(1)));
        lst.add(new Square(square.vertices().get(2), square.vertices().get(1), square.vertices().get(0), square.vertices().get(3)));
        return lst;
    }
}
