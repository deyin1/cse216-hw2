package arithmetic;

public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne>{

    @Override
    public PlusOrMinusOne binaryOperation(PlusOrMinusOne one, PlusOrMinusOne other) {
        if (one.equals(other)) return PlusOrMinusOne.ONE;
        else return PlusOrMinusOne.NEG_ONE;
    }

    @Override
    public PlusOrMinusOne identity() {
        return PlusOrMinusOne.ONE;
    }

    @Override
    public PlusOrMinusOne inverseOf(PlusOrMinusOne plusOrMinusOne) {
        if (plusOrMinusOne.equals(PlusOrMinusOne.ONE)) return PlusOrMinusOne.NEG_ONE;
        else return PlusOrMinusOne.ONE;
    }

    @Override
    public PlusOrMinusOne exponent(PlusOrMinusOne plusOrMinusOne, int k) {
        return Group.super.exponent(plusOrMinusOne, k);

    }
}
