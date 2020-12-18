package day18;

public class ArithmeticExpression implements Expression {
    Expression LH;
    Expression RH;
    Operator op;

    public ArithmeticExpression(Expression LH, Expression RH, Operator op) {
        this.LH = LH;
        this.RH = RH;
        this.op = op;
    }

    @Override
    public Long evaluate() {
        switch (op) {
            case ADD:
                return LH.evaluate() + RH.evaluate();
            case MUL:
                return LH.evaluate() * RH.evaluate();
            default:
                return null;
        }
    }
}
