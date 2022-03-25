package JSQL.abstractSyntaxTree.statement;

import JSQL.library.Functions;
import JSQL.library.Variables;

import java.util.List;
import java.util.Map;

public class FunctionDeclarationStatement implements Statement {

    private final String type;
    private final String name;
    private final Statement body;
    private final List<Map.Entry<String, String>> arguments;

    public FunctionDeclarationStatement(String type, String name, List<Map.Entry<String, String>> arguments, Statement body) {
        this.name = name;
        this.body = body;
        this.arguments = arguments;
        this.type = type;
    }

    @Override
    public void execute() {
        Functions.addFunction(type, name, body, arguments);
        Variables.addFunction(name);
    }

    @Override
    public String represent() {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arguments.size(); i++) {

            String type = null;
            if (arguments.get(i).getKey().equals("Num"))
                type = "Double";
            else type = arguments.get(i).getKey();

            builder.append(type)
                    .append(" ")
                    .append(arguments.get(i).getValue());

            if (i != arguments.size() - 1)
                builder.append(", ");

        }

        String t = null;
        if (type.equals("Num"))
            t = "Double";
        else t = type;

        String n = null;

        if (name.equals("main")) {
            t = "void";
            builder = new StringBuilder();
            builder.append("String [] args");
        }


        return "public static " + t + " " + name + "(" + builder + ") " + body.represent();
    }
}
