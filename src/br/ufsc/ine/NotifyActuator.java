package perceptionExperiment;

import java.util.List;

import br.ufsc.ine.agent.context.communication.Actuator;

public class NotifyActuator extends Actuator{
	private int counterMsg = 0;

	@Override
	public void act(List<String> args) {
		//System.out.println("tESTE");
		//runProducer(args);
		//Main.setValue("actuatorExperiment("+args.get(0)+")");
		counterMsg++;
		if(counterMsg == 3) {
			//MainKafka.setTimeStamp(System.currentTimeMillis(),"\n");
			MainKafka.runProducer(args);
			counterMsg = 0;
		}
		
		
	}
	
	
	

}
