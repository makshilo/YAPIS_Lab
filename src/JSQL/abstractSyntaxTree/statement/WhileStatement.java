package JSQL.abstractSyntaxTree.statement;

import JSQL.abstractSyntaxTree.expression.Expression;
import JSQL.exception.GrammarException;

public class WhileStatement implements Statement {

    private final Expression whileExpression;
    private final Statement whileStatement;


    public WhileStatement(Expression whileExpression, Statement whileStatement) {
        this.whileExpression = whileExpression;
        this.whileStatement = whileStatement;
    }

    @Override
    public void execute() {
        Object result = whileExpression.eval();

        if (!result.getClass().getSimpleName().equals("Boolean"))
            throw new GrammarException("Logical expression in if statement is wrong", 0);

        while ((Boolean) whileExpression.eval()) {
            whileStatement.execute();
        }


    }

    @Override
    public String represent() {
        return "while (" + whileExpression.represent() + ")\n" + whileStatement.represent();
    }
}
