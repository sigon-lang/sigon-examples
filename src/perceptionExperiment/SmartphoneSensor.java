package perceptionExperiment;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class SmartphoneSensor extends Sensor{	
	public static final PublishSubject<String> gps = PublishSubject.create();	
	public static final PublishSubject<String> headphone = PublishSubject.create();
	public static final PublishSubject<String> screen = PublishSubject.create();

	@Override
	public void run() {		
		gps.subscribe(super.publisher);
		headphone.subscribe(super.publisher);
		screen.subscribe(super.publisher);
		
		
	}

}
