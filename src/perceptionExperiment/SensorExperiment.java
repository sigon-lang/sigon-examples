package perceptionExperiment;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class SensorExperiment extends Sensor{
	public static final PublishSubject<String> msg = PublishSubject.create();


	@Override
	public void run() {
		msg.subscribe(super.publisher);		
	}

}
