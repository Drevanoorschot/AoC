package day18;

public enum Operator {
    MUL, ADD;

    static Operator fromString(String op) {
        switch (op) {
            case "+":
                return ADD;
            case "*":
                return MUL;
            default:
                return null;
        }
    }
}
