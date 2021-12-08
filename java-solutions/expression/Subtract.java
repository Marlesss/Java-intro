package expression;

public class Subtract extends BinaryOperation {
    public Subtract(SuperExpression first, SuperExpression second) {
        super(first, second);
    }

    @Override
    protected char getOperationChar() {
        return '-';
    }

    @Override
    protected int calc(int x, int y) {
        return x - y;
    }

}
