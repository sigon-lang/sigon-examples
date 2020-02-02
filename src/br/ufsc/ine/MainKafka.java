package br.ufsc.ine.perceptionExperiment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
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

import perceptionExperiment.kafka.ConsumerCreator;
import perceptionExperiment.kafka.ProducerCreator;

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
	
	
	private static final String profiling_file = "/home/rr/Documentos/timestamp.csv";
	private static String car;
	
	public static void main(String[] args) {
		//String[] fields = {"ciclo", "número de percepções", "Passiva/Ativa", "cc->bc", "plano", "cc->cc", "percepções válidas"};
		//String[] fields = {"kafka->cc" , "cc->bc", "plano", "cc->cc", "percepções válidas"};
		String[] fields = {"Inicio" , "Fim"};
		setHeader(fields);
		startAgent();
		
		consumeTopic = args[0];
		produtorTopic = args[1];
		//broker = args[3];
		//perceptConsumer("car(veh0, no)");
		//perceptConsumer("car(veh0, no)");
/*		setValue(i+""); //ciclo atual
		setValue(1+""); //quantidade total de percepcoes
		i++;
		setValue("Ativa");*/
		//perceptConsumer("car(chevete)", "yes", "yes");
		runConsumer();

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
	
	static void runProducer(List<String> argsAct) {
		Producer<Long, String> producer = ProducerCreator.createProducer();
		System.out.println("Enviando mensagem: "+argsAct.get(0));
		//setValue("actuatorExperiment("+argsAct.get(0)+")");
		
		String msg = argsAct.get(0)+";"+car+";"+System.currentTimeMillis();
		//System.out.println(msg);
		final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(produtorTopic,
				msg);
		
				
		try {
			//setTimeStamp(System.currentTimeMillis(),"Fim\n");
			
			RecordMetadata metadata = producer.send(record).get();
			setTimeStamp(metadata.timestamp(),"\n");
			
			//System.out.println("Meu timestamp "+metadata.timestamp());
			//System.out.println("Record sent with key " + argsAct.get(0) + " to partition " + metadata.partition()
				//	+ " with offset " + metadata.offset());
			
			//profiling(startTime);
			

		} catch (ExecutionException e) {
			System.out.println("Error in sending record");
			System.out.println(e);
		} catch (InterruptedException e) {
			System.out.println("Error in sending record");
			System.out.println(e);
		}
	}
	private static long startTime;
	
	static void runConsumer() {
		Consumer<Long, String> consumer = ConsumerCreator.createConsumer(consumeTopic);		

		while (true) {
			final ConsumerRecords<Long, String> consumerRecords = consumer.poll(100);
			/*if (consumerRecords.count() == 0) {
				noMessageToFetch++;
				if (noMessageToFetch > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
					break;
				else
					continue;
			}*/
			
			

			
			consumerRecords.forEach(record -> {
				startTime = System.nanoTime();
				//System.out.println("Inicio msg: "+startTime);
				//Timestamp tp = new Timestamp(System.currentTimeMillis());
				/*System.out.println("Record Key " + record.key());
				System.out.println("Record value " + record.value());
				record.value();
				System.out.println("Record partition " + record.partition());
				System.out.println("Record offset " + record.offset());*/
				//System.out.println("Timestamp: "+record.timestamp()); 
				setTimeStamp(record.timestamp(),"");
				//System.out.println("offset "+record.offset());
				
				car = record.value();
				perceptConsumer(record.value(), "yes", "yes");
				//profiling(startTime);
				
			});
			
			consumer.commitAsync();
		}
		//consumer.close();
	}
	
	
	public static void setTimeStamp(long timestamp, String quebraLinha) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(profiling_file, true));
			writer.append(timestamp + ";"+quebraLinha);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void startAgent(){
	    try {

	       
	    	File agentFile = new File("/home/rr/experimentos/sigon-examples/awareness.on");

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
			agent.setProfilingFile(profiling_file);

	        agent.run(agentWalker, cc);

	    } catch (IOException e) {
	        System.out.println("I/O exception.");
	    }
	}
	
	public static int i = 0;
	
	private static void perceptConsumer(String percept, String soundPerception, String screenPerception){
        //System.out.println("Percept");
        
        SmartphoneSensor.screenSensor.onNext("screen("+screenPerception+").");
        SmartphoneSensor.soundSensor.onNext("sound("+soundPerception+").");//para cada sensor é feito um ciclo de raciocinio
        CommunicationSensor.approachingCar.onNext(percept+".");
//        CommunicationSensor.approachingCar.onNe(percept+".");
        
		
        
     /*   System.out.println("CC "+CommunicationContextService.getInstance().getTheory());
        System.out.println("BC "+BeliefsContextService.getInstance().getTheory().toString());	
        System.out.println("RB "+BayesianContextService.getInstance().getBeliefs().toString());
		

        System.out.println("DC " +DesiresContextService.getInstance().getTheory());
        System.out.println("PC " +PlansContextService.getInstance().getTheory().toString());
        System.out.println("IC "+IntentionsContextService.getInstance().getTheory());
        System.out.println("CC " +CommunicationContextService.getInstance().getTheory());*/
        
        
        

    }
	
	
		 
	    private static void profiling(long startTime) {
			if (profiling_file != null) {
				long endTime = System.nanoTime();
				Timestamp tp = new Timestamp(System.currentTimeMillis());
				System.out.println(tp);
				long duration = (endTime - startTime) / 1000000;
				System.out.println(endTime);
				System.out.println("Duracao: "+duration);
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(profiling_file, true));
					writer.append(duration + ";");
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}


}
