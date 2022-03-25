package JSQL.abstractSyntaxTree.expression.type;

import JSQL.abstractSyntaxTree.expression.Expression;

public class NumberExpression implements Expression {

    private double value;

    public NumberExpression(double value) {
        this.value = value;
    }

    @Override
    public Object eval() {
        return value;
    }

    @Override
    public String represent() {
        return value + "";
    }
}
