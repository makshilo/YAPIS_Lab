package JSQL.abstractSyntaxTree.expression;

import JSQL.exception.GrammarException;
import JSQL.library.Variables;
import support.Column;
import support.Row;

public class LineGetExpression implements Expression {

    private String name;
    private Expression position;

    public LineGetExpression(String name, Expression position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public Object eval() {

        Object value = Variables.getValue(name);


        Double p = (Double) position.eval();

        if ((double) p.intValue() != p)
            throw new GrammarException("wrong table access", 0);


        if (Variables.checkTypes("Row", value) || Variables.checkTypes("Column", value)) {

            String result = "";

            try {
                result = ((Row) value).getElement(p.intValue());
            } catch (ClassCastException e) {
                result = ((Column) value).getElement(p.intValue());
            }

            return result;

        }
        throw new GrammarException("wrong usage of var and [] operators", 0);
    }

    @Override
    public String represent() {
        return name + ".getElement(" + position.represent() + ")";
    }
}
