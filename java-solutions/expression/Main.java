package expression;

public class Main {
    public static void main(String[] args) {
        TripleExpression expression = new Subtract(
                new Multiply(
                        new Const(2),
                        new Variable("x")
                ),
                new Const(3)
        );
        System.out.println(expression.evaluate(5, 4, 3));
        System.out.println(expression);
    }
}
