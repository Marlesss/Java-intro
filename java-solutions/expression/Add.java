package expression;

public class Add extends BinaryOperation {
    public Add(SuperExpression first, SuperExpression second) {
        super(first, second);
    }

    @Override
    protected char getOperationChar() {
        return '+';
    }

}
