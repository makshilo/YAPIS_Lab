package JSQL.abstractSyntaxTree.expression.type;

import JSQL.abstractSyntaxTree.expression.Expression;
import JSQL.exception.GrammarException;
import mainTypes.Table;

public class TableExpression implements Expression {

    private Table table;
    private final Expression rows;
    private final Expression columns;

    public TableExpression(Expression rows, Expression columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public Object eval() {

        Double r = (Double) rows.eval();
        Double c = (Double) columns.eval();

        if (((double) r.intValue() != r) || ((double) c.intValue() != c))
            throw new GrammarException("wrong table access", 0);

        table = new Table(r.intValue(), c.intValue());

        return table;
    }

    @Override
    public String represent() {
        return "new Table(" + rows.represent() + ", " + columns.represent() + ")";
    }

    @Override
    public String toString() {
        return table + "";
    }
}
