package JSQL.abstractSyntaxTree.statement;

import JSQL.abstractSyntaxTree.expression.Expression;

public class ReturnStatement extends RuntimeException implements Statement {

    private final Expression expression;
    private Object result;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }


    public Object getResult() {
        return result;
    }

    @Override
    public void execute() {
        result = expression.eval();
        throw this;
    }

    @Override
    public String represent() {
        return "return " + expression.represent() + ";";
    }
}
