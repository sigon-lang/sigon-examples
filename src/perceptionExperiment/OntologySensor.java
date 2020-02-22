package perceptionExperiment;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class OntologySensor extends Sensor{
	public static final PublishSubject<String> query = PublishSubject.create();
	

	@Override
	public void run() {
		query.subscribe(super.publisher);
		
		
	}

}
