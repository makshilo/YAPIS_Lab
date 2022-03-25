package JSQL.abstractSyntaxTree.statement;

import JSQL.abstractSyntaxTree.expression.Expression;

public class PrintStatement implements Statement {

    private final Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {

        Object result = expression.eval();

        if (result instanceof String)
            result = ((String) result).replaceAll("\"", "");

        System.out.println(result);
    }

    @Override
    public String represent() {
        return "System.out.println(" + expression.represent() + ");";
    }
}
