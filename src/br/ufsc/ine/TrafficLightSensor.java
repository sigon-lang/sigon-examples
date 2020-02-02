package perceptionExperiment;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class TrafficLightSensor extends Sensor{
	public static final PublishSubject<String> status = PublishSubject.create();
	

	@Override
	public void run() {
		status.subscribe(super.publisher);
		
		
	}

}
