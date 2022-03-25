package JSQL.abstractSyntaxTree.statement;

import JSQL.abstractSyntaxTree.expression.Expression;
import JSQL.exception.GrammarException;

public class IfElseStatement implements Statement {

    private final Expression ifExpression;
    private final Statement ifStatement;
    private final Statement elseStatement;

    public IfElseStatement(Expression ifExpression, Statement ifStatement, Statement elseStatement) {
        this.ifExpression = ifExpression;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public void execute() {

        Object result = ifExpression.eval();

        if (!result.getClass().getSimpleName().equals("Boolean"))
            throw new GrammarException("Logical expression in if statement is wrong", 0);


        if ((Boolean) result) {
            ifStatement.execute();
        } else elseStatement.execute();

    }

    @Override
    public String represent() {
        return "if (" + ifExpression.represent() + ")\n" + ifStatement.represent() + "else\n" + elseStatement.represent();
    }
}
