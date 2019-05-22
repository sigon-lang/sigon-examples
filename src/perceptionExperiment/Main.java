package perceptionExperiment;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import agent.AgentLexer;
import agent.AgentParser;
import br.ufsc.ine.agent.Agent;
import br.ufsc.ine.agent.context.beliefs.BeliefsContextService;
import br.ufsc.ine.agent.context.communication.CommunicationContextService;
import br.ufsc.ine.agent.context.desires.DesiresContextService;
import br.ufsc.ine.agent.context.intentions.IntentionsContextService;
import br.ufsc.ine.agent.context.plans.PlansContextService;

import br.ufsc.ine.parser.AgentWalker;
import br.ufsc.ine.parser.VerboseListener;

public class Main {
	
	private static final String profiling_file = "/home/rodrigor/Documentos/exp1.csv";

	public static void main(String[] args) {
		startAgent();
		percept();
	}
	
	
	private static void startAgent() {
		try {

			File agentFile = new File("/home/rodrigor/sigon/sigon-examples/src/perceptionExperiment/percepcaoAtiva.on");
			//File agentFile = new File("percepcaoPassiva.on");
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
			agent.setProfilingFile(profiling_file);
			agent.run(agentWalker);

		} catch (IOException e) {
			System.out.println("I/O exception.");
		}
	}
	
	 private static void percept(){
	        
	        SensorExperiment.msg.onNext("perception(x).");
	        
			
	        
	        System.out.println("CC "+CommunicationContextService.getInstance().getTheory());
	        System.out.println("BC "+BeliefsContextService.getInstance().getTheory().toString());
	        System.out.println("DC " +DesiresContextService.getInstance().getTheory());
	        System.out.println("PC " +PlansContextService.getInstance().getTheory().toString());
	        System.out.println("IC "+IntentionsContextService.getInstance().getTheory());
	        System.out.println("CC " +CommunicationContextService.getInstance().getTheory());
	        
	        
	        

	    }


}
