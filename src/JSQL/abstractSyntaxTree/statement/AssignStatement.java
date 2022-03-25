package JSQL.abstractSyntaxTree.statement;

import JSQL.abstractSyntaxTree.expression.Expression;
import JSQL.library.Variables;

import java.util.Objects;

public class AssignStatement implements Statement {

    private String type;
    private String variable;
    private Expression expression;

    public AssignStatement(String type, String variable, Expression expression) {
        this.type = type;
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public void execute() {

        Object result = expression.eval();

        if (Objects.isNull(type))
            Variables.setValue(variable, result);
        else
            Variables.addVariable(type, variable, result);


    }

    @Override
    public String represent() {

        String type;

        if (Objects.isNull(this.type))
            type = "";
        else {

            if (this.type.equals("Num"))
                type = "Double ";
            else
                type = this.type + " ";
        }


        return type + variable + " = " + expression.represent() + ";";
    }
}
