package JSQL.abstractSyntaxTree.expression;

import JSQL.library.Variables;

public class VariableExpression implements Expression {

    private String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Object eval() {
        return Variables.getValue(name);
    }

    @Override
    public String represent() {
        return name;
    }

}
