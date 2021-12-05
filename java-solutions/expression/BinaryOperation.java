package expression;

abstract class BinaryOperation implements SuperExpression {
    protected final SuperExpression first;
    protected final SuperExpression second;


    public BinaryOperation(SuperExpression first, SuperExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int evaluate(int x) {
        if (getOperationChar() == '+') {
            return first.evaluate(x) + second.evaluate(x);
        }
        if (getOperationChar() == '-') {
            return first.evaluate(x) - second.evaluate(x);
        }
        if (getOperationChar() == '*') {
            return first.evaluate(x) * second.evaluate(x);
        }
        return first.evaluate(x) / second.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (getOperationChar() == '+') {
            return first.evaluate(x, y, z) + second.evaluate(x, y, z);
        }
        if (getOperationChar() == '-') {
            return first.evaluate(x, y, z) - second.evaluate(x, y, z);
        }
        if (getOperationChar() == '*') {
            return first.evaluate(x, y, z) * second.evaluate(x, y, z);
        }
        return first.evaluate(x, y, z) / second.evaluate(x, y, z);
    }


    @Override
    public String toString() {
        return "(" + first.toString() + " " + getOperationChar() + " " + second.toString() + ")";
    }

    public boolean equals(Object obj) {
        if (obj instanceof BinaryOperation) {
            BinaryOperation other = (BinaryOperation) obj;
            return first.equals(other.first) && getOperationChar() == other.getOperationChar() &&
                    second.equals(other.second);
        }
        return false;
    }

    public int hashCode() {
        return (first.hashCode() * 17 + second.hashCode()) * 17 + getOperationChar();
    }

    protected abstract char getOperationChar();
}
