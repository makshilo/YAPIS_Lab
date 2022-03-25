package JSQL.abstractSyntaxTree.statement;

import JSQL.abstractSyntaxTree.expression.Expression;
import JSQL.exception.GrammarException;
import JSQL.library.Variables;
import mainTypes.Table;

public class TableSetElementStatement implements Statement {

    private final String name;
    private final Expression row;
    private final Expression column;
    private final Expression expression;

    public TableSetElementStatement(String name, Expression row, Expression column, Expression expression) {
        this.name = name;
        this.row = row;
        this.column = column;
        this.expression = expression;
    }


    @Override
    public void execute() {

        Object value = Variables.getValue(name);
        Object exp = expression.eval();

        Double r = (Double) row.eval();
        Double c = (Double) column.eval();

        if (((double) r.intValue() != r) || ((double) c.intValue() != c))
            throw new GrammarException("wrong table access", 0);


        if (Variables.checkTypes("Table", value)
                && Variables.checkTypes("String", exp)) {

            Table result = (Table) Variables.getValue(name);
            result.set(r.intValue(), c.intValue(), (String) exp);

        } else throw new GrammarException("incompatable typese for var and [] ops", 0);

    }

    @Override
    public String represent() {
        return name + ".set(" + row.represent() + ", " + column.represent() + ", " + expression.represent() + ");";
    }
}
