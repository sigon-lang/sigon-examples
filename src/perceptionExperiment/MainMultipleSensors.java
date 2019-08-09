package perceptionExperiment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

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
import rx.subjects.PublishSubject;

public class MainMultipleSensors {

	private static final String profiling_file = "/home/rr/Documentos/expCadaPercept2.csv";

	public static void main(String[] args) {
		String[] fields = {"ciclo", "número de percepções", "Passiva/Ativa", "cc->bc", "plano", "cc->cc", "percepções válidas"};
		setHeader(fields);
		startAgent();
		int total = 1;
		long startTime = 0;  	  	
		for (int i = 0; i < total; i++) {
			setValue(i+""); //ciclo atual
			setValue(1+""); //quantidade total de percepcoes			
			setValue("Ativa");
			//startTime = System.nanoTime();
			percept(i);
			perceptAux(i);
			//reasoningCycle(startTime, i+1);
			
		}
		

	}
	
	public static void setValue(String value) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(profiling_file, true));
			writer.append(value+";");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setHeader(String[] fields) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(profiling_file, true));
			for (String string : fields) {
				writer.append(string + ";");
				
			}
			writer.append(System.lineSeparator());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void startAgent() {
		try {

			File agentFile = new File("/home/rr/sigon/sigon-examples/src/perceptionExperiment/pedestrian");
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

	private static void percept(int index) {

		
		CarSensor.color.onNext("color(a0, red).");
		CarSensor.position.onNext("position(a0, 0, 0, 0).");
		CarSensor.license.onNext("license(a0, aaabbb).");
		CarSensor.sound.onNext("sound(a0, none).");
		CarSensor.model.onNext("model(a0, car).");
		
		PersonSensor.height.onNext("height(p0, 180).");
		PersonSensor.position.onNext("position(p0, 0, 0, 0).");
		PersonSensor.unknown.onNext("unknown(p0, true).");
		
		TrafficLightSensor.status.onNext("trafficLightStatus(t0, red).");
		
		SmartphoneSensor.connectedHeadphones.onNext("connectedHeadPhones(true).");
		SmartphoneSensor.connectedHeadphones.onNext("connectedHeadPhones(false).");

		SmartphoneSensor.incommingMessages.onNext("incommingMessage(msg0, read).");
		SmartphoneSensor.incommingMessages.onNext("incommingMessage(msg1, unread).");
		SmartphoneSensor.typing.onNext("typing(true).");
		SmartphoneSensor.visionImpaired.onNext("visionImpaired(true).");
		
		MessageReceiverSensor.message.onNext("message(agent0, content)");
		

		System.out.println("CC " + CommunicationContextService.getInstance().getTheory());
		System.out.println("BC " + BeliefsContextService.getInstance().getTheory().toString());
		System.out.println("DC " + DesiresContextService.getInstance().getTheory());
		System.out.println("PC " + PlansContextService.getInstance().getTheory().toString());
		System.out.println("IC " + IntentionsContextService.getInstance().getTheory());
		System.out.println("CC " + CommunicationContextService.getInstance().getTheory());

	}
	
private static void perceptAux(int index) {

		
		CarSensor.color.onNext("color(a0, red).");
		CarSensor.position.onNext("position(a0, 0, 0, 0).");
		CarSensor.license.onNext("license(a0, aaabbb).");
		CarSensor.sound.onNext("sound(a0, none).");
		CarSensor.model.onNext("model(a0, car).");
		
		PersonSensor.height.onNext("height(p0, 180).");
		PersonSensor.position.onNext("position(p0, 0, 0, 0).");
		PersonSensor.unknown.onNext("unknown(p0, true).");
		
		TrafficLightSensor.status.onNext("trafficLightStatus(t0, red).");
		
		SmartphoneSensor.connectedHeadphones.onNext("connectedHeadPhones(false).");
		SmartphoneSensor.incommingMessages.onNext("incommingMessage(msg0, read).");
		SmartphoneSensor.incommingMessages.onNext("incommingMessage(msg1, unread).");
		SmartphoneSensor.typing.onNext("typing(true).");
		SmartphoneSensor.visionImpaired.onNext("visionImpaired(true).");
		
		MessageReceiverSensor.message.onNext("message(agent0, content)");
		

		System.out.println("CC " + CommunicationContextService.getInstance().getTheory());
		System.out.println("BC " + BeliefsContextService.getInstance().getTheory().toString());
		System.out.println("DC " + DesiresContextService.getInstance().getTheory());
		System.out.println("PC " + PlansContextService.getInstance().getTheory().toString());
		System.out.println("IC " + IntentionsContextService.getInstance().getTheory());
		System.out.println("CC " + CommunicationContextService.getInstance().getTheory());

	}

	
	private static void reasoningCycle(long startTime, int cycle) {
		if (profiling_file != null) {
			long endTime = System.nanoTime();
			long duration = (endTime - startTime) / 1000000;
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(profiling_file, true));
				writer.append(duration+";"+System.lineSeparator());
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void perceptFromFile(String fileName) {

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(SensorExperiment.msg::onNext);

		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * System.out.println("CC " +
		 * CommunicationContextService.getInstance().getTheory());
		 * System.out.println("BC " +
		 * BeliefsContextService.getInstance().getTheory().toString());
		 * System.out.println("DC " + DesiresContextService.getInstance().getTheory());
		 * System.out.println("PC " +
		 * PlansContextService.getInstance().getTheory().toString());
		 * System.out.println("IC " +
		 * IntentionsContextService.getInstance().getTheory()); System.out.println("CC "
		 * + CommunicationContextService.getInstance().getTheory());
		 */

	}

}
