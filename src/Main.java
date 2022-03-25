import JSQL.abstractSyntaxTree.statement.Statement;
import JSQL.lexer.Lexer;
import JSQL.lexer.Token;
import JSQL.library.Functions;
import JSQL.library.Variables;
import JSQL.parser.Parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 2; i++) {
            Scanner scanner = new Scanner(Path.of("src/source/sample" + (i + 1) + ".ns"));
            List<String> lines = new ArrayList<>();
            while (scanner.hasNext())
                lines.add(scanner.nextLine());

            Lexer lexer = new Lexer(lines);
            List<Token> tokens = lexer.lexAnalyse();

            Parser parser = new Parser(tokens);
            List<Statement> statements = parser.parse();
            statements.forEach(Statement::execute);

            Statement main = Functions.getBody("main");
            if (!Objects.isNull(main))
                Variables.changeFunction("main");
            else {
                System.out.println("main doesn't exist");
                return;
            }
            main.execute();

            PrintWriter pw = new PrintWriter("src/test/Test" + (i + 1) + ".java");

            StringBuilder builder = new StringBuilder();
            builder.append("package test;\n");
            builder.append("import support.Table;\n");
            builder.append("import support.Collection;\n");
            builder.append("import support.Row;\n");
            builder.append("import support.Column;\n");
            builder.append("public class Test").append(i + 1).append(" {\n");

            statements.forEach(s -> builder.append(s.represent()).append("\n"));

            builder.append("}");

            pw.write(builder.toString());
            pw.close();

            Functions.clear();
            Variables.clear();
        }
    }


}
