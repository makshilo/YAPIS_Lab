package JSQL.abstractSyntaxTree.statement;

import JSQL.abstractSyntaxTree.expression.Expression;
import JSQL.exception.GrammarException;
import JSQL.library.Variables;

public class ForStatement implements Statement {


    private final String type;
    private final String name;
    private final Statement assign;
    private final Expression expression;
    private final Statement change;
    private final Statement block;

    public ForStatement(String type, String name, Statement assign, Expression expression, Statement change, Statement block) {
        this.type = type;
        this.name = name;
        this.assign = assign;
        this.expression = expression;
        this.change = change;
        this.block = block;
    }

    @Override
    public void execute() {
        if (!type.equals("Num")) throw new GrammarException("for should have Num iterator type", 0);

        assign.execute();

        for (Double i = (Double) Variables.getValue(name); (Boolean) expression.eval(); change.execute()) {
            block.execute();
        }
        Variables.deleteVariable(name);
    }

    @Override
    public String represent() {

        String chg = change.represent();

        return "for(" + assign.represent() + " " + expression.represent() + "; " + chg.substring(0, chg.length() - 1) + ")\n"
                + block.represent();
    }
}
