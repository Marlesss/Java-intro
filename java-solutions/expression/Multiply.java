package expression;

public class Multiply extends BinaryOperation {
    public Multiply(SuperExpression first, SuperExpression second) {
        super(first, second);

    }

    @Override
    protected char getOperationChar() {
        return '*';
    }

}
