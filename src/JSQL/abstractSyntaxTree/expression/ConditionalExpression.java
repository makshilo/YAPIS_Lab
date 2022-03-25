package JSQL.abstractSyntaxTree.expression;

import JSQL.exception.GrammarException;
import JSQL.exception.IncompatibleType;
import support.Column;
import support.Row;
import support.Table;

public class ConditionalExpression implements Expression {
    private Expression firstExpression;
    private Expression secondExpression;
    private String operation;

    public ConditionalExpression(String operation, Expression firstExpression, Expression secondExpression) {
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

        if (first instanceof Double || first instanceof String) {
            switch (operation) {
                case "==" -> result = (firstExpression.eval()).equals(secondExpression.eval());
                case "!=" -> result = !(firstExpression.eval()).equals(secondExpression.eval());
                case ">" -> result = ((Comparable) firstExpression.eval()).compareTo((Comparable) secondExpression.eval()) > 0;
                case "<" -> result = ((Comparable) firstExpression.eval()).compareTo((Comparable) secondExpression.eval()) < 0;
                default -> throw new GrammarException("unexpected operator", 0);
            }
        } else if (first instanceof Table || first instanceof Column || first instanceof Row) {
            throw new GrammarException("Table doesn't override" + operation + "operator", 0);
        }

        return result;

    }

    @Override
    public String represent() {

        String result = null;

        switch (operation) {
            case "==" -> result = "(" + firstExpression.represent() + ").equals(" + secondExpression.represent() + ")";
            case "!=" -> result = "!(" + firstExpression.represent() + ").equals(" + secondExpression.represent() + ")";
            case ">" -> result = "(" + firstExpression.represent() + ").compareTo(" + secondExpression.represent() + ") > 0";
            case "<" -> result = "(" + firstExpression.represent() + ").compareTo(" + secondExpression.represent() + ") < 0";
            default -> result = "(" + firstExpression.represent() + ").equals(" + secondExpression.represent() + ")";
        }

        return result;
    }
}
