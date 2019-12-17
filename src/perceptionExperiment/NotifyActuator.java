package perceptionExperiment;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.gaurav.kafka.constants.IKafkaConstants;
import com.gaurav.kafka.producer.ProducerCreator;

import br.ufsc.ine.agent.context.communication.Actuator;
import br.ufsc.ine.context.bayesian.BayesianContextService;

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
