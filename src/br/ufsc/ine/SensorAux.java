package perceptionExperiment;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class SensorAux extends Sensor{
	public static final PublishSubject<String> msg2 = PublishSubject.create();
	 


	@Override
	public void run() {
		msg2.subscribe(super.publisher);		
 

	}


}
