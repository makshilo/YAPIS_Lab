package JSQL.abstractSyntaxTree.expression;

import JSQL.exception.IncompatibleType;

public class TypeCastingExpression implements Expression {

    private String type;
    private Expression expression;

    public TypeCastingExpression(String type, Expression expression) {
        this.type = type;
        this.expression = expression;
    }

    @Override
    public Object eval() {


        Object result = expression.eval();

        if (type.equals("String")) {
            result = result.toString();
            return result;
        } else if (type.equals("Num") && result instanceof String) {
            double d = 0;
            try {
                String str = ((String) result).substring(1, ((String) result).length() - 1);
                d = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                throw new IncompatibleType("wrong type casting", 0);
            }
            result = d;
            return result;
        }


        throw new IncompatibleType("wrong type during casting", 0);


    }

    @Override
    public String represent() {

        if (type.equals("String"))
            return "(\"\" + " + expression.represent() + ")";
        else
            return "(Double.parseDouble(" + expression.represent() + "))";

    }
}
