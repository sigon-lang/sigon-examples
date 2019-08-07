package perceptionExperiment;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class PersonSensor extends Sensor{
	public static final PublishSubject<String> height = PublishSubject.create();
	public static final PublishSubject<String> unknown = PublishSubject.create();
	public static final PublishSubject<String> position = PublishSubject.create();
	


	@Override
	public void run() {
		height.subscribe(super.publisher);
		unknown.subscribe(super.publisher);
		position.subscribe(super.publisher);
		
	}

}
