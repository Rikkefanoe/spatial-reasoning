package annotations.ast;

public class RealValue extends Value{
    public double v;
    public String s;

    @Override
    public String toString(){
        return s;
        // return Double.toString(v);
    }

}
