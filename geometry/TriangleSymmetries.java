package geometry;

import java.util.Collection;

import java.util.*;

public class TriangleSymmetries implements Symmetries<EqTriangle>{
    @Override
    public boolean areSymmetric(EqTriangle s1, EqTriangle s2) {
        Collection<EqTriangle> col = symmetriesOf(s1);
        List<Double> x = new ArrayList<>(3);
        List<Double> y = new ArrayList<>(3);
        for (Point p : s2.vertices()){
            x.add(p.getX());
            y.add(p.getY());
        }
        for (EqTriangle tri : col){
            List<Double> xs = new ArrayList<>(3);
            List<Double> ys = new ArrayList<>(3);
            for (Point p : tri.vertices()){
                xs.add(p.getX());
                ys.add(p.getY());
            }
            if (x.equals(xs) && y.equals(ys)) return true;
        }
        return false;
    }

    @Override
    public Collection<EqTriangle> symmetriesOf(EqTriangle eqTriangle) {
        ArrayList<EqTriangle> lst = new ArrayList<>();
        lst.add(eqTriangle);
        lst.add(eqTriangle.rotateBy(120));
        lst.add(eqTriangle.rotateBy(240));
        lst.add(new EqTriangle(eqTriangle.vertices().get(0), eqTriangle.vertices().get(2), eqTriangle.vertices().get(1)));
        lst.add(new EqTriangle(eqTriangle.vertices().get(2), eqTriangle.vertices().get(1), eqTriangle.vertices().get(0)));
        lst.add(new EqTriangle(eqTriangle.vertices().get(1), eqTriangle.vertices().get(0), eqTriangle.vertices().get(2)));
        return lst;
    }
}
