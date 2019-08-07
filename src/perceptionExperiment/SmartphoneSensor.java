package perceptionExperiment;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class SmartphoneSensor extends Sensor{
	public static final PublishSubject<String> typing = PublishSubject.create();
	public static final PublishSubject<String> connectedHeadphones = PublishSubject.create();
	public static final PublishSubject<String> incommingMessages = PublishSubject.create();
	public static final PublishSubject<String> visionImpaired = PublishSubject.create();


	@Override
	public void run() {
		typing.subscribe(super.publisher);
		connectedHeadphones.subscribe(super.publisher);
		incommingMessages.subscribe(super.publisher);
		visionImpaired.subscribe(super.publisher);
		
		
	}

}
