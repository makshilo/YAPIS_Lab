package JSQL.abstractSyntaxTree.expression;

import JSQL.exception.GrammarException;
import JSQL.exception.IncompatibleType;
import support.Column;
import support.Row;
import support.Table;

public class BinaryExpression implements Expression {

    private Expression firstExpression;
    private Expression secondExpression;
    private String operation;

    public BinaryExpression(String operation, Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operation = operation;
    }

    @Override
    public Object eval() {

        Object first = firstExpression.eval();
        Object second = secondExpression.eval();
        if (first.getClass() != second.getClass()) throw new IncompatibleType("Incompatible types", 0);

        Object result = null;

        if (first instanceof Double) {
            switch (operation) {
                case "+" -> result = (Double) firstExpression.eval() + (Double) secondExpression.eval();
                case "-" -> result = (Double) firstExpression.eval() - (Double) secondExpression.eval();
                case "*" -> result = (Double) firstExpression.eval() * (Double) secondExpression.eval();
                case "/" -> result = (Double) firstExpression.eval() / (Double) secondExpression.eval();
                default -> throw new GrammarException("unexpected operator", 0);
            }
        } else if (first instanceof String) {
            switch (operation) {
                case "+" -> result = firstExpression.eval().toString() + secondExpression.eval().toString();
                default -> throw new GrammarException("String doesn't override" + operation + "operator", 0);
            }
        } else if (first instanceof Table || first instanceof Column || first instanceof Row) {
            throw new GrammarException("Table doesn't override" + operation + "operator", 0);
        }

        return result;

    }

    @Override
    public String represent() {
        return "(" + firstExpression.represent() + " " + operation + " " + secondExpression.represent() + ")";
    }
}
