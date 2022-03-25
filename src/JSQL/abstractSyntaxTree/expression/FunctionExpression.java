package JSQL.abstractSyntaxTree.expression;

import JSQL.abstractSyntaxTree.statement.ReturnStatement;
import JSQL.abstractSyntaxTree.statement.Statement;
import JSQL.exception.GrammarException;
import JSQL.library.Functions;
import JSQL.library.Variables;

import java.util.ArrayList;
import java.util.List;

public class FunctionExpression implements Expression {

    private String name;
    private List<Expression> arguments;

    public FunctionExpression(String name, List<Expression> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public Object eval() {

        var parArgs = Functions.getArguments(name);
        List<Object> values = new ArrayList<>();
        if (parArgs.size() == arguments.size()) {
            for (Expression e : arguments)
                values.add(e.eval());
        } else {
            throw new GrammarException("Not equal count of arguments", 0);
        }

        Statement body = Functions.getBody(name);
        String previous = Variables.getCurrentFunName();
        Variables.changeFunction(name);

        for (int i = 0; i < values.size(); i++) {
            var entry = parArgs.get(i);
            String type = entry.getKey();
            String name = entry.getValue();
            Variables.addVariable(type, name, values.get(i));
        }
        //execution
        Object result = null;

        try {
            body.execute();
        } catch (ReturnStatement returnStatement) {
            result = returnStatement.getResult();
        }

        String type = Functions.getType(name);
        checkTypeConditionals(type, result);

        Variables.cleanFunction(name);
        Variables.changeFunction(previous);

        return result;
    }

    private void checkTypeConditionals(String type, Object result) {
        if (!Variables.checkTypes(type, result))
            throw new GrammarException("return doesn't match fun type", 0);
    }


    @Override
    public String represent() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < arguments.size(); i++) {
            builder.append(arguments.get(i).represent());

            if (i != arguments.size() - 1)
                builder.append(", ");

        }


        return name + "(" + builder + ")";
    }
}
