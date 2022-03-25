package JSQL.abstractSyntaxTree.expression.type;

import JSQL.abstractSyntaxTree.expression.Expression;
import JSQL.exception.GrammarException;
import JSQL.library.Variables;
import support.Table;

public class RowExpression implements Expression {

    private String name;
    private Expression row;

    public RowExpression(String name, Expression row) {
        this.name = name;
        this.row = row;
    }

    @Override
    public Object eval() {
        Object value = Variables.getValue(name);

        Double r = (Double) row.eval();

        if ((double) r.intValue() != r)
            throw new GrammarException("wrong table access", 0);

        if (Variables.checkTypes("Table", value)) {
            return ((Table) value).getRow(r.intValue());
        }

        throw new GrammarException("wrong usage of var and [] operators", 0);
    }

    @Override
    public String represent() {
        return name + ".getRow(" + row.represent() + ")";
    }

}
