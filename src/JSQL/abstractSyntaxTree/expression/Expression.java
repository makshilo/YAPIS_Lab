package JSQL.abstractSyntaxTree.expression;

public interface Expression {
    Object eval();

    String represent();
}
