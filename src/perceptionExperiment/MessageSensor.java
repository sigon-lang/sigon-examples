package perceptionExperiment;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class MessageSensor extends Sensor{
	public static final PublishSubject<String> vehicle = PublishSubject.create();
	


	@Override
	public void run() {
		vehicle.subscribe(super.publisher);
		
		
		
	}

}
