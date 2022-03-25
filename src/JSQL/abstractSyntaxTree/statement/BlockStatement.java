package JSQL.abstractSyntaxTree.statement;

import java.util.ArrayList;
import java.util.List;

public class BlockStatement implements Statement {

    private List<Statement> statements;

    public BlockStatement() {
        this.statements = new ArrayList<>();
    }


    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void execute() {
        for (Statement s : statements)
            s.execute();
    }

    @Override
    public String represent() {

        StringBuilder builder = new StringBuilder();

        builder.append("{").append("\n");

        for (Statement s : statements)
            builder.append(s.represent()).append("\n");

        builder.append("}").append("\n");

        return builder.toString();

    }
}
