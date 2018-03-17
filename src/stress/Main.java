package stress;

import agent.AgentLexer;
import agent.AgentParser;
import br.ufsc.ine.agent.Agent;
import br.ufsc.ine.agent.context.beliefs.BeliefsContextService;
import br.ufsc.ine.parser.AgentWalker;
import br.ufsc.ine.parser.VerboseListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {
        startAgent();
        startStress();

    }


    private static void startStress(){
        System.out.println("Iniciando stress");
        StressSensor.publish.onNext("test.");

        String fileName = "/home/valdirluiz/testes-stress/testComplexity";


        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(StressSensor.publish::onNext);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finalizando stress");

        System.out.println(BeliefsContextService.getInstance().getTheory().toString());

    }

    private static void startAgent(){
        try {

            File agentFile = new File("agentstress");
            CharStream stream = CharStreams.fromFileName(agentFile.getAbsolutePath());
            AgentLexer lexer = new AgentLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            AgentParser parser = new AgentParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new VerboseListener());

            ParseTree tree = parser.agent();
            ParseTreeWalker walker = new ParseTreeWalker();

            AgentWalker agentWalker = new AgentWalker();
            walker.walk(agentWalker, tree);

            Agent agent = new Agent();
            agent.run(agentWalker);



        } catch (IOException e) {
            System.out.println("I/O exception.");
        }
    }
}
