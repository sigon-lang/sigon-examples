package perceptionExperiment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.gaurav.kafka.constants.IKafkaConstants;
import com.gaurav.kafka.consumer.ConsumerCreator;
import com.gaurav.kafka.producer.ProducerCreator;

import agent.AgentLexer;
import agent.AgentParser;
import br.ufsc.ine.agent.Agent;
import br.ufsc.ine.agent.context.ContextService;
import br.ufsc.ine.agent.context.beliefs.BeliefsContextService;
import br.ufsc.ine.agent.context.communication.CommunicationContextService;
import br.ufsc.ine.agent.context.desires.DesiresContextService;
import br.ufsc.ine.agent.context.intentions.IntentionsContextService;
import br.ufsc.ine.agent.context.plans.PlansContextService;
import br.ufsc.ine.context.bayesian.BayesianContextService;
import br.ufsc.ine.parser.AgentWalker;
import br.ufsc.ine.parser.VerboseListener;

public class MainKafka {
	static String produtorTopic;
	static String consumeTopic;
	static String broker;
	
	public static void main(String[] args) {
		startAgent();
		
		consumeTopic = args[0];
		produtorTopic = args[1];
		//broker = args[3];
		//perceptConsumer("car(veh0, no)");
		//perceptConsumer("car(veh0, no)");
		
		runConsumer();

	}
	
	static void runProducer(List<String> argsAct) {
		Producer<Long, String> producer = ProducerCreator.createProducer();

		for (int index = 0; index < 2; index++) {
			final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(produtorTopic,
					"This is record " + index);
			
			try {
				RecordMetadata metadata = producer.send(record).get();
				System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
						+ " with offset " + metadata.offset());
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}
		}
	}
	
	
	static void runConsumer() {
		Consumer<Long, String> consumer = ConsumerCreator.createConsumer(consumeTopic);
		

		int noMessageToFetch = 0;
		

		while (true) {
			final ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
			/*if (consumerRecords.count() == 0) {
				noMessageToFetch++;
				if (noMessageToFetch > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
					break;
				else
					continue;
			}*/

			consumerRecords.forEach(record -> {
				/*System.out.println("Record Key " + record.key());
				System.out.println("Record value " + record.value());
				record.value();
				System.out.println("Record partition " + record.partition());
				System.out.println("Record offset " + record.offset());*/
				
				perceptConsumer(record.value());
				
			});
			consumer.commitAsync();
		}
		//consumer.close();
	}
	
	
	private static void startAgent(){
	    try {

	       
	    	File agentFile = new File("/home/rr/sigon/sigon-examples/awareness.on");

	        CharStream stream = CharStreams.fromFileName(agentFile.getAbsolutePath());
	        AgentLexer lexer = new AgentLexer(stream);
	        CommonTokenStream tokens = new CommonTokenStream(lexer);

	        AgentParser parser = new AgentParser(tokens);
	        parser.removeErrorListeners();
	        parser.addErrorListener(new VerboseListener());

	        ParseTree tree = parser.agent();
	        ParseTreeWalker walker = new ParseTreeWalker();
	        System.out.println(tree.toStringTree(parser));
	        

	        AgentWalker agentWalker = new AgentWalker();
	        walker.walk(agentWalker, tree);
	        
	        BayesianContextService bc =  BayesianContextService.getInstance();
	        
	        
	        ContextService[] cc = new ContextService[] {bc};
	        Agent agent = new Agent();	    
	        agent.run(agentWalker, cc);

	    } catch (IOException e) {
	        System.out.println("I/O exception.");
	    }
	}
	
	private static void perceptConsumer(String percept){
        System.out.println("Percept");

       
       //ReadMessage.msg.onNext("enterAuction(house).");	        
       // ReadMessage.msg.onNext("jobOffer/(salary(7000, 5000), time(5, 6)).");
        //ReadMessage.msg.onNext("salaryOptions(7000, 10000, 12000).");
        
        /*jobOffer(
		salary(7000, 10000, 12000),
		jobDescription(qa, programmer, teamManager, projectManager),
		car(leased, noLeased, noAgreement),
		pension(0, 10, 20, noAgreement),
		promotion(2, 4, noAgreement),
		workingHours(8, 9, 10)
		)*/
        
        //SmartphoneSensor.connectedHeadphones.onNext("car(chevete).");
        SmartphoneSensor.connectedHeadphones.onNext(percept+".");	        
		
        
        System.out.println("CC "+CommunicationContextService.getInstance().getTheory());
        System.out.println("BC "+BeliefsContextService.getInstance().getTheory().toString());	
        System.out.println("RB "+BayesianContextService.getInstance().getBeliefs().toString());
		

        System.out.println("DC " +DesiresContextService.getInstance().getTheory());
        System.out.println("PC " +PlansContextService.getInstance().getTheory().toString());
        System.out.println("IC "+IntentionsContextService.getInstance().getTheory());
        System.out.println("CC " +CommunicationContextService.getInstance().getTheory());
        
        
        

    }
	
	
	 private static void percept(){
	        System.out.println("Percept");

	       
	       //ReadMessage.msg.onNext("enterAuction(house).");	        
	       // ReadMessage.msg.onNext("jobOffer/(salary(7000, 5000), time(5, 6)).");
	        //ReadMessage.msg.onNext("salaryOptions(7000, 10000, 12000).");
	        
	        /*jobOffer(
			salary(7000, 10000, 12000),
			jobDescription(qa, programmer, teamManager, projectManager),
			car(leased, noLeased, noAgreement),
			pension(0, 10, 20, noAgreement),
			promotion(2, 4, noAgreement),
			workingHours(8, 9, 10)
			)*/
	        
	        //SmartphoneSensor.connectedHeadphones.onNext("car(chevete).");
	        SmartphoneSensor.connectedHeadphones.onNext("car(chevete, yes).");	        
			
	        
	        System.out.println("CC "+CommunicationContextService.getInstance().getTheory());
	        System.out.println("BC "+BeliefsContextService.getInstance().getTheory().toString());	
	        System.out.println("RB "+BayesianContextService.getInstance().getBeliefs().toString());
			

	        System.out.println("DC " +DesiresContextService.getInstance().getTheory());
	        System.out.println("PC " +PlansContextService.getInstance().getTheory().toString());
	        System.out.println("IC "+IntentionsContextService.getInstance().getTheory());
	        System.out.println("CC " +CommunicationContextService.getInstance().getTheory());
	        
	        
	        

	    }
	 
	 private static void perceptRemove(){
	        System.out.println("Percept");

	       
	       //ReadMessage.msg.onNext("enterAuction(house).");	        
	       // ReadMessage.msg.onNext("jobOffer/(salary(7000, 5000), time(5, 6)).");
	        //ReadMessage.msg.onNext("salaryOptions(7000, 10000, 12000).");
	        
	        /*jobOffer(
			salary(7000, 10000, 12000),
			jobDescription(qa, programmer, teamManager, projectManager),
			car(leased, noLeased, noAgreement),
			pension(0, 10, 20, noAgreement),
			promotion(2, 4, noAgreement),
			workingHours(8, 9, 10)
			)*/
	        
	        //SmartphoneSensor.connectedHeadphones.onNext("car(chevete).");
	        SmartphoneSensor.connectedHeadphones.onNext("car(chevete, no).");	        
			
	        
	        System.out.println("CC "+CommunicationContextService.getInstance().getTheory());
	        System.out.println("BC "+BeliefsContextService.getInstance().getTheory().toString());	
	        System.out.println("RB "+BayesianContextService.getInstance().getBeliefs().toString());
			

	        System.out.println("DC " +DesiresContextService.getInstance().getTheory());
	        System.out.println("PC " +PlansContextService.getInstance().getTheory().toString());
	        System.out.println("IC "+IntentionsContextService.getInstance().getTheory());
	        System.out.println("CC " +CommunicationContextService.getInstance().getTheory());
	        
	        
	        

	    }


}
