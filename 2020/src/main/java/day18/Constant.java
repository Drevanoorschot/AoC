package day18;

public class Constant implements Expression {
    long val;

    Constant(int val) {
        this.val = val;
    }

    Constant(String val) {
        this.val = Integer.parseInt(val);
    }

    @Override
    public Long evaluate() {
        return val;
    }
}
