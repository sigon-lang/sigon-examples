package perceptionExperiment;

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

import agent.AgentLexer;
import agent.AgentParser;
import alice.util.Sleep;
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
import perceptionExperiment.kafka.ConsumerCreator;
import perceptionExperiment.kafka.ProducerCreator;

public class MainFinal {
	static String produtorTopic;
	static String consumeTopic;
	static String broker;
	static String notify = "notNotify(pedestrian)";
	
	
	//private static String profiling_file = "/home/thiago/projetos/timestamp.csv";
	private static String profiling_file = "/home/rr/timestamp.csv";
	private static String car;
	
	
	public static void main(String[] args){
		//String[] fields = {"ciclo", "número de percepções", "Passiva/Ativa", "cc->bc", "plano", "cc->cc", "percepções válidas"};
		//String[] fields = {"kafka->cc" , "cc->bc", "plano", "cc->cc", "percepções válidas"};
		String[] fields = {"Inicio" , "Fim"};
		//profiling_file = args[0];
		//setHeader(fields);
		
		startAgentPath(args[0]);
		System.out.println("Teste");
		System.out.println("CC "+CommunicationContextService.getInstance().getTheory());
        System.out.println("BC "+BeliefsContextService.getInstance().getTheory().toString());	
        System.out.println("RB "+BayesianContextService.getInstance().getBeliefs().toString());
		
		percept("car(chevete, yes);yes;yes");

        System.out.println("DC " +DesiresContextService.getInstance().getTheory());
        System.out.println("PC " +PlansContextService.getInstance().getTheory().toString());
        System.out.println("IC "+IntentionsContextService.getInstance().getTheory());
        System.out.println("CC " +CommunicationContextService.getInstance().getTheory());
        System.out.println(getAction());
        notify = "CARALHOOOOOOO";
        System.out.println(getAction());
        while(true) {
        	try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	//System.out.println(getAction());
        }
        
		

		
	//	while(true) {
			
		//	 System.out.println("T");
		//}
		
		/*CommunicationSensor.approachingCar.onNext("car(x,yes).");
		SmartphoneSensor.soundSensor.onNext("sound(yes).");
		SmartphoneSensor.screenSensor.onNext("screen(yes).");*/
		
		
		
		
		
		 
			//percept("car(chevete, yes);yes;yes");

			//System.out.println(getAction());

		

        
		
		//System.out.println(execute("car(x);yes;yes")); 
		//perceptConsumer("car(chevete)", "yes", "yes");
		//consumeTopic = args[0];
		//podutorTopic = args[1];
		//broker = args[3];
		//perceptConsumer("car(veh0, no)");
		//perceptConsumer("car(veh0, no)");
/*		setValue(i+""); //ciclo atual
		setValue(1+""); //quantidade total de percepcoes
		i++;
		setValue("Ativa");*/
		//perceptConsumer("car(chevete)", "yes", "yes");
		//runConsumer();

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
	
		
	
	public static void setTimeStamp(long timestamp, String quebraLinha) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(profiling_file, true));
			writer.append(timestamp + ";"+quebraLinha);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Agent agent = new Agent();
	
	
		
	public static void startAgentPath(String path){
	    try {

	       
	    	File agentFile = new File(path);

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
	        
	        BayesianContextService bc =  BayesianContextService.getInstance();
	        
	        
	        ContextService[] cc = new ContextService[] {bc};
			//agent.setProfilingFile(profiling_file);

	        agent.run(agentWalker, cc);
	        
	        System.out.println("AGINDO");

	    } catch (IOException e) {
	        System.out.println("I/O exception.");
	    }
	}

	
	public static String executeSimples() {
		return "Teste";
	}
	

	
	public static void percept(String percept) {
		//SmartphoneSensor.screenSensor.onNext("screen("+screenPerception+").");
        //SmartphoneSensor.soundSensor.onNext("sound("+soundPerception+").");//para cada sensor é feito um ciclo de raciocinio
        //CommunicationSensor.approachingCar.onNext(percept+".");
		
		//startAgent();		
		//perceptConsumer("car(chevete)", "yes", "yes");
		
		String[] percepts = percept.split(";");
		
		
		CommunicationSensor.approachingCar.onNext(percepts[0]+".");
		SmartphoneSensor.soundSensor.onNext("sound("+percepts[1]+").");
		SmartphoneSensor.screenSensor.onNext("screen("+percepts[2]+").");


		/*if(notify.equalsIgnoreCase("notNotify(pedestrian)")) {
			notify = "notify(pedestrian)";
			
		}else {
			notify = "notNotify(pedestrian)";
		}*/
		
		
	}
	
	public static int i = 0;
    private final static Object lock = new Object();
	
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

		public static void setNotify(List<String> args) {
			
			System.out.println("Act");
			notify = args.get(0);

/*			synchronized (lock) {
				notify = args.get(0);
				lock.notify();
			
	        }*/			
		}
		
		public static String getAction() {
			
			
			
			/*startAgentPath("/home/rr/awareness.on");
			 * percept("car(chevete, yes);yes;yes");*/
			 
			System.out.println("Teste");
			System.out.println("CC "+CommunicationContextService.getInstance().getTheory());
	        System.out.println("BC "+BeliefsContextService.getInstance().getTheory().toString());	
	        System.out.println("RB "+BayesianContextService.getInstance().getBeliefs().toString());
			
			

			/*synchronized (lock) {
				try {
					lock.wait();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

	        }*/
			return notify;

		    
		}


}
