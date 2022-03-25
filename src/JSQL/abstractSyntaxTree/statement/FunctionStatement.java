package JSQL.abstractSyntaxTree.statement;

import JSQL.abstractSyntaxTree.expression.Expression;

public class FunctionStatement implements Statement {

    private final String name;
    private final Expression expression;

    public FunctionStatement(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public void execute() {
        expression.eval();
    }

    @Override
    public String represent() {
        return expression.represent() + ";";
    }
}
