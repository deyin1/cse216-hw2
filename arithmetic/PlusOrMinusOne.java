package arithmetic;

public enum PlusOrMinusOne {
    ONE,
    NEG_ONE;

    public String toString(){
        if(this.equals(ONE)) return "1";
        else return "-1";
    }



}
