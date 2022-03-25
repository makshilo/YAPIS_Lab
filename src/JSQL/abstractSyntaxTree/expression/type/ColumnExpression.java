package JSQL.abstractSyntaxTree.expression.type;

import JSQL.abstractSyntaxTree.expression.Expression;
import JSQL.exception.GrammarException;
import JSQL.library.Variables;
import mainTypes.Table;

public class ColumnExpression implements Expression {

    private final String name;
    private final Expression column;

    public ColumnExpression(String name, Expression column) {
        this.name = name;
        this.column = column;
    }

    @Override
    public Object eval() {
        Object value = Variables.getValue(name);

        Double c = (Double) column.eval();

        if ((double) c.intValue() != c)
            throw new GrammarException("wrong table access", 0);

        if (Variables.checkTypes("Table", value)) {
            return ((Table) value).getColumn(c.intValue());
        }

        throw new GrammarException("wrong usage of var and [] operators", 0);
    }

    @Override
    public String represent() {
        return name + ".getColumn(" + column.represent() + ")";
    }
}
