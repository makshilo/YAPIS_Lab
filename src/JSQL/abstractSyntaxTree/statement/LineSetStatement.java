package JSQL.abstractSyntaxTree.statement;

import JSQL.abstractSyntaxTree.expression.Expression;
import JSQL.exception.GrammarException;
import JSQL.library.Variables;
import support.Collection;

public class LineSetStatement implements Statement {

    private final String name;
    private final Expression position;
    private final Expression expression;

    public LineSetStatement(String name, Expression position, Expression expression) {
        this.name = name;
        this.position = position;
        this.expression = expression;
    }

    @Override
    public void execute() {
        Object value = Variables.getValue(name);
        Object exp = expression.eval();

        Double p = (Double) position.eval();

        if ((double) p.intValue() != p)
            throw new GrammarException("wrong table access", 0);

        if ((Variables.checkTypes("Row", value) || Variables.checkTypes("Column", value))
                && Variables.checkTypes("String", exp)) {
            Collection result = (Collection) Variables.getValue(name);
            result.setElement(p.intValue(), (String) exp);
        } else throw new GrammarException("incompatable typese for var and [] ops", 0);
    }

    @Override
    public String represent() {
        return name + ".setElement(" + position.represent() + ", " + expression.represent() + ");";
    }
}
