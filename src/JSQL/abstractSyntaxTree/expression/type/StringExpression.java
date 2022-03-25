package JSQL.abstractSyntaxTree.expression.type;

import JSQL.abstractSyntaxTree.expression.Expression;

public class StringExpression implements Expression {

    private String value;

    public StringExpression(String value) {
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
