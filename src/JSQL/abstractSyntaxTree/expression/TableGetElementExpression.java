package JSQL.abstractSyntaxTree.expression;

import JSQL.exception.GrammarException;
import JSQL.library.Variables;
import support.Table;

public class TableGetElementExpression implements Expression {

    private String name;
    private Expression row;
    private Expression column;

    public TableGetElementExpression(String name, Expression row, Expression column) {
        this.name = name;
        this.row = row;
        this.column = column;
    }

    @Override
    public Object eval() {

        Object value = Variables.getValue(name);

        Double r = (Double) row.eval();
        Double c = (Double) column.eval();

        if (((double) r.intValue() != r) || ((double) c.intValue() != c))
            throw new GrammarException("wrong table access", 0);

        if (Variables.checkTypes("Table", value))
            return ((Table) value).get(r.intValue(), c.intValue());

        throw new GrammarException("wrong usage of var and [] operators", 0);
    }

    @Override
    public String represent() {
        return name + ".get(" + row.represent() + ", " + column.represent() + ")";
    }
}
