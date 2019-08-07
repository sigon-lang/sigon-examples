package perceptionExperiment;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class MessageReceiverSensor extends Sensor{
	public static final PublishSubject<String> message = PublishSubject.create();
	


	@Override
	public void run() {
		message.subscribe(super.publisher);
		
		
		
	}

}
