package expression;

public class Divide extends BinaryOperation {
    public Divide(SuperExpression first, SuperExpression second) {
        super(first, second);
    }

    @Override
    protected char getOperationChar() {
        return '/';
    }

}
